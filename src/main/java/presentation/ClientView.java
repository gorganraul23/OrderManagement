package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea ferestrei pentru operatii cu Client
 */
public class ClientView extends JFrame{

    private JLabel l = new JLabel("Clients");
    private JLabel l_id = new JLabel("Id: ");
    private JTextField tf_id = new JTextField(4);
    private JLabel l_name = new JLabel("Name: ");
    private JTextField tf_name = new JTextField(15);
    private JLabel l_address = new JLabel("Address: ");
    private JTextField tf_address = new JTextField(15);
    private JLabel l_email = new JLabel("Email: ");
    private JTextField tf_email = new JTextField(15);
    private JButton btnInsert = new JButton("Insert");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private JButton btnViewAll = new JButton("View all");
    //private JTable table = new JTable();

    /**
     * Constructorul clasei in care se adauga toate componentele necesare
     */
    public ClientView(){

        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(4, 2));
        p1.add(l_id);
        p1.add(tf_id);
        p1.add(l_name);
        p1.add(tf_name);
        p1.add(l_address);
        p1.add(tf_address);
        p1.add(l_email);
        p1.add(tf_email);

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
        //p3.add(table);
        //p3.add(Box.createRigidArea(new Dimension(0, 15)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(10, 0)));
        content.add(p3);
        content.add(Box.createRigidArea(new Dimension(10, 0)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Clients");
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
     * Getter pentru adresa introdusa de utilizator
     * @return adresa introdusa de utilizator
     */
    public String getAddressInput(){
        return tf_address.getText();
    }

    /**
     * Getter pentru email-ul introdus de utilizator
     * @return email-ul introdus de utilizator
     */
    public String getEmailInput(){
        return tf_email.getText();
    }

}