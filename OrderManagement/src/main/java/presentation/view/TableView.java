package presentation.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;

/**
 * @author Technical University of Cluj-Napoca, Petrache Remus-Mircea
 * @since Apr 19, 2021
 */
public class TableView extends JFrame {

    /**
     * View for the table with all the elements
     * @param tableHeader header of the table
     * @param tableData data of the table
     * @param title title of the view
     */
    public TableView(String[] tableHeader, Object[][] tableData, String title) {
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setBounds(100, 100, 450, 300);
        setTitle(title);

        JPanel contentPane = new JPanel();
        contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
        contentPane.setLayout(new BorderLayout(0, 0));
        setContentPane(contentPane);

        JScrollPane scrollPane = new JScrollPane();
        scrollPane.getViewport().setBackground(new Color(158, 219, 236));
        contentPane.add(scrollPane, BorderLayout.CENTER);
        JTable table = new JTable(tableData, tableHeader);
        table.getColumnModel().getColumn(0).setPreferredWidth(25);
        table.getTableHeader().setBackground(new Color(140, 202, 220));
        table.getTableHeader().setFont(new Font("Tahoma", Font.BOLD, 12));
        table.setBackground(new Color(158, 219, 236));
        table.setEnabled(false);

        scrollPane.setViewportView(table);
    }
}
