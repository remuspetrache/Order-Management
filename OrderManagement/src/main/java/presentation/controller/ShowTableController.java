package presentation.controller;

import bll.ClientBLL;
import bll.OrderBLL;
import bll.ProductBLL;
import presentation.view.TableView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class ShowTableController implements ActionListener {

    private List<String> tableHeader;
    private List<List<Object>> tableData;
    private final ClientBLL clientBLL;
    private final ProductBLL productBLL;
    private final OrderBLL orderBLL;
    private final String title;

    /**
     * Instantiates the clientBLL, productBLL and orderBLL parameters and sets the view's title
     * @param title view's title
     */
    public ShowTableController(String title) {
        this.title = title;
        this.clientBLL = new ClientBLL();
        this.productBLL = new ProductBLL();
        this.orderBLL = new OrderBLL();
    }

    /**
     * Transforms header list to header array
     * @return header as an array
     */
    private String[] getHeaderArray() {
        String[] tableHeaderArray = new String[tableHeader.size()];
        int i = 0;
        for(String s : tableHeader){
            tableHeaderArray[i] = tableHeader.get(i++);
        }
        return tableHeaderArray;
    }

    /**
     * Transforms data list to data matrix
     * @return data as a matrix
     */
    private Object[][] getDataMatrix() {
        Object[][] tableDataMatrix = new Object[tableData.size()][];
        int i = 0;
        for (Object o : tableData) {
            tableDataMatrix[i] = tableData.get(i++).toArray();
        }
        return tableDataMatrix;
    }

    /**
     * Displays the table with all the elements, depending on the type of element chosen
     * @param e click on the view button
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if(title.equalsIgnoreCase("client")){
            this.tableHeader = clientBLL.createTableHeader();
            this.tableData = clientBLL.createTableData();
        } else if(title.equalsIgnoreCase("product")){
            this.tableHeader = productBLL.createTableHeader();
            this.tableData = productBLL.createTableData();
        } else if(title.equalsIgnoreCase("order")){
            this.tableHeader = orderBLL.createTableHeader();
            this.tableData = orderBLL.createTableData();
        }
        TableView tableView = new TableView(getHeaderArray(), getDataMatrix(), title);
        tableView.setVisible(true);
    }
}
