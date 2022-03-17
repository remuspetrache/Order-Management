package dao;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

import connection.ConnectionFactory;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 18, 2021
 */
public class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;

    /**
     * Constructor that instantiates the generic object
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    /**
     * Creates query used to select a certain field
     * @param field field to be selected
     * @return query as a string
     */
    private String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT ");
        sb.append(" * ");
        sb.append(" FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ").append(field).append(" =?");
        return sb.toString();
    }

    /**
     * Finds all the elements in a table
     * @return list with all the elements in the table
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String query = "SELECT * FROM " + type.getSimpleName();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(query);
            resultSet = statement.executeQuery();
            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Finds an element in the table with a given primary key
     * @param id primary key of the element to be searched
     * @return the element found
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        try {
            connection = ConnectionFactory.getConnection();
            String idName = getPrimaryKeys(connection).get(0);
            String query = createSelectQuery(idName);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();
            List<T> objects = createObjects(resultSet);
            if (objects.isEmpty()) {
                return null;
            }
            return objects.get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: findById" + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Creates a list of objects from a result set
     * @param resultSet result set obtained from execution of statement
     * @return list of objects obtained from the result set through reflection
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (Constructor constructor : ctors) {
            ctor = constructor;
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Finds all the primary keys in a table
     * @param connection connection with the database
     * @return list of the primary keys in the table
     */
    private ArrayList<String> getPrimaryKeys(Connection connection) {
        ResultSet rs;
        ArrayList<String> primaryKeys = new ArrayList<>();
        try {
            DatabaseMetaData meta = connection.getMetaData();
            rs = meta.getPrimaryKeys(null, null, type.getSimpleName());

            while (rs.next()) {
                primaryKeys.add(rs.getString("COLUMN_NAME"));
            }
        } catch (SQLException throwable) {
            throwable.printStackTrace();
        }
        return primaryKeys;
    }

    /**
     * Finds all the fields that are not primary keys in a table
     * @param connection connection with the database
     * @return list of the fields that are not primary keys
     */
    private ArrayList<Field> getStatementFields(Connection connection) {
        ArrayList<Field> statementFields = new ArrayList<>();
        ArrayList<String> primaryKeys = getPrimaryKeys(connection);
        for (Field field : type.getDeclaredFields()) {
            boolean ok = true;
            for (String s : primaryKeys) {
                if (s.equals(field.getName())) {
                    ok = false;
                    break;
                }
            }
            if (ok) {
                statementFields.add(field);
            }
        }
        return statementFields;
    }

    /**
     * Creates a query for inserting a new element
     * @param connection connection to the database
     * @return query as a string
     */
    private String createInsertQuery(Connection connection) {
        StringBuilder sb1 = new StringBuilder();
        sb1.append("INSERT ");
        sb1.append("INTO ");
        sb1.append(type.getSimpleName());
        sb1.append(" (");
        StringBuilder sb2 = new StringBuilder();
        sb2.append(" VALUES (");

        String prefix = "";
        for (Field field : getStatementFields(connection)) {
            sb1.append(prefix);
            sb2.append(prefix);
            prefix = ",";
            sb1.append(field.getName());
            sb2.append("?");
        }
        sb1.append(")");
        sb2.append(")");
        sb1.append(sb2);
        return sb1.toString();
    }

    /**
     * Prepares a statement by setting all the corresponding objects
     * @param connection connection to the database
     * @param query statement's query to be prepared
     * @param t object whose fields have to be used to prepare the statement
     * @return prepared statement
     */
    private PreparedStatement prepareUpdateStatement(Connection connection, String query, T t) throws SQLException, IntrospectionException, InvocationTargetException, IllegalAccessException {
        PreparedStatement statement = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);
        int i = 1;
        for (Field field : getStatementFields(connection)) {
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
            Method method = propertyDescriptor.getReadMethod();
            statement.setObject(i++, method.invoke(t));
        }
        return statement;
    }

    /**
     * Inserts a new element in a table
     * @param t element to be inserted
     * @return primary key of the inserted element
     */
    public int insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        int insertedID = -1;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createInsertQuery(connection);
            statement = prepareUpdateStatement(connection, query, t);
            statement.executeUpdate();
            ResultSet rs = statement.getGeneratedKeys();
            if (rs.next()) {
                insertedID = rs.getInt(1);
            }
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: insert " + e.getMessage());
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return insertedID;
    }

    /**
     * Creates query for the update statement of an element
     * @param connection connection with database
     * @return query as a string
     */
    private String createUpdateQuery(Connection connection) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ");
        sb.append(type.getSimpleName());
        sb.append(" SET ");

        String prefix = "";
        for (Field field : getStatementFields(connection)) {
            sb.append(prefix);
            prefix = ",";
            sb.append(field.getName()).append(" = ?");
        }
        sb.append(" WHERE ");
        String idName = getPrimaryKeys(connection).get(0);
        sb.append(idName).append(" = ?");
        return sb.toString();
    }

    /**
     * Update an element with a given primary key to a new element
     * @param id primary key of the element to be updated
     * @param t new element information
     */
    public void update(int id, T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createUpdateQuery(connection);
            statement = prepareUpdateStatement(connection, query, t);
            statement.setInt(getStatementFields(connection).size() + 1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: update " + e.getMessage());
        } catch (IllegalAccessException | IntrospectionException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates a query for the delete statement of an element
     * @param connection connection to database
     * @return query as a string
     */
    private String createDeleteQuery(Connection connection) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE ");
        sb.append("FROM ");
        sb.append(type.getSimpleName());
        sb.append(" WHERE ");
        sb.append(getPrimaryKeys(connection).get(0)).append(" = ?");
        return sb.toString();
    }

    /**
     * Deletes an element with a given primary key
     * @param id primary key of the element to be deleted
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        try {
            connection = ConnectionFactory.getConnection();
            String query = createDeleteQuery(connection);
            statement = connection.prepareStatement(query);
            statement.setInt(1, id);
            statement.executeUpdate();
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO: delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }

    /**
     * Creates the header of the table with all elements
     * @return list of column names
     */
    public List<String> createTableHeader() {
        List<String> tableHeader = new ArrayList<>();
        for (Field field : type.getDeclaredFields()) {
            tableHeader.add(field.getName());
        }
        return tableHeader;
    }

    /**
     * Creates the data in the table with all the elements
     * @return matrix with all data
     */
    public List<List<Object>> createTableData() {
        List<List<Object>> tableData = new ArrayList<>();
        for (T t : findAll()) {
            List<Object> tableRow = new ArrayList<>();
            for (Field field : type.getDeclaredFields()) {
                try {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), type);
                    Method method = propertyDescriptor.getReadMethod();
                    tableRow.add(method.invoke(t));
                } catch (IllegalAccessException | InvocationTargetException | IntrospectionException e) {
                    e.printStackTrace();
                }
            }
            tableData.add(tableRow);
        }
        return tableData;
    }
}
