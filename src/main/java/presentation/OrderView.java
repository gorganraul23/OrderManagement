package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea ferestrei pentru operatii cu Order
 */
public class OrderView extends JFrame{

    private JLabel l = new JLabel("Orders");
    private JLabel l_id = new JLabel("Id: ");
    private JTextField tf_id = new JTextField(4);
    private JLabel l_client_id = new JLabel("Client id: ");
    private JTextField tf_client_id = new JTextField(15);
    private JLabel l_product_id = new JLabel("Product id: ");
    private JTextField tf_product_id = new JTextField(15);
    private JLabel l_quantity = new JLabel("Quantity: ");
    private JTextField tf_quantity = new JTextField(15);
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnViewAll = new JButton("View all");

    /**
     * Constructorul clasei in care se adauga toate componentele necesare
     */
    public OrderView(){

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        p1.add(l_id);
        p1.add(tf_id);
        p1.add(l_client_id);
        p1.add(tf_client_id);
        p1.add(l_product_id);
        p1.add(tf_product_id);
        p1.add(l_quantity);
        p1.add(tf_quantity);

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
        this.setTitle("Orders");
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
     * @param infoMessage mesajul de eroare
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
     * Getter pentru id-ul clientului introdus de utilizator
     * @return id-ul clientului introdus de utilizator
     */
    public String getClientIdInput(){
        return tf_client_id.getText();
    }

    /**
     * Getter pentru id-ul produsului introdus de utilizator
     * @return id-ul produsului introdus de utilizator
     */
    public String getProductIdInput(){
        return tf_product_id.getText();
    }

    /**
     * Getter pentru cantitatea introdusa de utilizator
     * @return cantitatea introdusa de utilizator
     */
    public String getQuantityInput(){
        return tf_quantity.getText();
    }
}