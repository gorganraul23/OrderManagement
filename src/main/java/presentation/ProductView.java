package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea ferestrei pentru operatii cu Produs
 */
public class ProductView extends JFrame{

    private JLabel l = new JLabel("Products");
    private JLabel l_id = new JLabel("Id: ");
    private JTextField tf_id = new JTextField(4);
    private JLabel l_name = new JLabel("Name: ");
    private JTextField tf_name = new JTextField(15);
    private JLabel l_price = new JLabel("Price: ");
    private JTextField tf_price = new JTextField(15);
    private JLabel l_stock = new JLabel("Stock: ");
    private JTextField tf_stock = new JTextField(15);
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnViewAll = new JButton("View all");

    /**
     * Constructorul clasei in care se adauga toate componentele necesare
     */
    public ProductView(){

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        p1.add(l_id);
        p1.add(tf_id);
        p1.add(l_name);
        p1.add(tf_name);
        p1.add(l_price);
        p1.add(tf_price);
        p1.add(l_stock);
        p1.add(tf_stock);

        JPanel p2 = new JPanel();
        p2.setLayout(new BoxLayout(p2, BoxLayout.X_AXIS));
        p2.add(btnInsert);
        p2.add(btnUpdate);
        p2.add(btnDelete);
        p2.add(btnViewAll);

        JPanel p3 = new JPanel();
        p3.setLayout(new BoxLayout(p3, BoxLayout.Y_AXIS));
        p3.add(Box.createRigidArea(new Dimension(0, 10)));
        p3.add(l);
        l.setFont(new Font("Sheriff", Font.BOLD, 19));
        l.setAlignmentX(CENTER_ALIGNMENT);
        p3.add(Box.createRigidArea(new Dimension(0, 15)));
        p3.add(p1);
        p3.add(Box.createRigidArea(new Dimension(0, 15)));
        p3.add(p2);
        p3.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(10, 0)));
        content.add(p3);
        content.add(Box.createRigidArea(new Dimension(10, 0)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Products");
        this.setDefaultCloseOperation(JFrame.HIDE_ON_CLOSE);
    }

    /**
     * Metoda adauga actionListener pe butonul Insert
     * @param al obiect de tip actionListener
     */
    public void addInsertListener(ActionListener al) {
        btnInsert.addActionListener(al);
    }

    /**
     * Metoda adauga actionListener pe butonul Update
     * @param al obiect de tip actionListener
     */
    public void addUpdateListener(ActionListener al) {
        btnUpdate.addActionListener(al);
    }

    /**
     * Metoda adauga actionListener pe butonul Delete
     * @param al obiect de tip actionListener
     */
    public void addDeleteListener(ActionListener al) {
        btnDelete.addActionListener(al);
    }

    /**
     * Metoda adauga actionListener pe butonul ViewAll
     * @param al obiect de tip actionListener
     */
    public void addViewAllListener(ActionListener al) {
        btnViewAll.addActionListener(al);
    }

    /**
     * Metoda deschide o fereastra prin care transmite un mesaj de eroare utilizatorului
     * @param errMessage mesajul de eroare
     */
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

    /**
     * Metoda deschide o fereastra prin care transmite un mesaj de informare utilizatorului
     * @param infoMessage mesajul de informare
     */
    void showInfo(String infoMessage){
        JOptionPane.showMessageDialog(this, infoMessage);
    }

    /**
     * Getter pentru id-ul introdus de utilizator
     * @return id-ul introdus de utilizator
     */
    public String getIdInput(){
        return tf_id.getText();
    }

    /**
     * Getter pentru numele introdus de utilizator
     * @return numele introdus de utilizator
     */
    public String getNameInput(){
        return tf_name.getText();
    }

    /**
     * Getter pentru pretul introdus de utilizator
     * @return pretul introdus de utilizator
     */
    public String getPriceInput(){
        return tf_price.getText();
    }

    /**
     * Getter pentru stocul introdus de utilizator
     * @return stocul introdus de utilizator
     */
    public String getStockInput(){
        return tf_stock.getText();
    }
}