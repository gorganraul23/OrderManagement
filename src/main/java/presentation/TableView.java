package presentation;

import Utility.ReflectionClass;

import javax.swing.*;
import java.awt.*;

/**
 * Clasa care se ocupa de formarea ferestrei care contine tabelul
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class TableView extends JFrame {

    private JTable table;
    private JPanel panel;

    /**
     * Constructorul ferestrei
     * @param modelObject obiect de tip model
     * @param objectBLL obiect de tip logica
     */
    public TableView(Object modelObject, Object objectBLL) {
        panel = new JPanel();
        createTable(modelObject, objectBLL);
        createScrollBar();

        this.setTitle("Table");
        this.add(panel);
        this.setSize(600, 200);
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
        this.setVisible(true);
    }

    public void setTable(JTable table) {
        this.table = table;
    }

    /**
     * Metoda apeleaza metoda din reflection pentru a face tabelul
     * @param modelObject obiect de tip model
     * @param objectBLL obiect de tip logica
     */
    private void createTable(Object modelObject, Object objectBLL){
        table = ReflectionClass.retrieveInformation(modelObject, objectBLL);
        table.setPreferredScrollableViewportSize(new Dimension(550, 100));
        table.setFillsViewportHeight(true);
    }

    /**
     * Creeaza un ScrollBar
     */
    private void createScrollBar(){
        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setVisible(true);
        panel.add(scrollPane);
    }
}
