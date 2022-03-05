package presentation;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

/**
 * Clasa care se ocupa de realizarea ferestrei principale
 */
public class View extends JFrame {

    private JLabel l = new JLabel("Order Management");
    private JLabel lIntr = new JLabel("Select what to edit and press Go! ");
    private JComboBox combo = new JComboBox(new String[] {"Client", "Product", "Order"});
    private JButton btnGo = new JButton("Go!");

    /**
     * Constructorul clasei in care se adauga toate componentele necesare
     */
    public View(){
        JPanel p1 = new JPanel();
        p1.setLayout(new GridLayout(9, 1));
        p1.add(Box.createRigidArea(new Dimension(15, 10)));
        p1.add(l);
        l.setAlignmentX(CENTER_ALIGNMENT);
        l.setFont(new Font("Sheriff", Font.BOLD, 19));
        p1.add(Box.createRigidArea(new Dimension(15, 15)));
        p1.add(lIntr);
        p1.add(Box.createRigidArea(new Dimension(15, 10)));
        p1.add(combo);
        combo.setAlignmentX(CENTER_ALIGNMENT);
        p1.add(Box.createRigidArea(new Dimension(15, 10)));
        p1.add(btnGo);
        btnGo.setAlignmentX(CENTER_ALIGNMENT);
        btnGo.setBackground(Color.gray);
        p1.add(Box.createRigidArea(new Dimension(15, 10)));

        JPanel content = new JPanel();
        content.setLayout(new BoxLayout(content, BoxLayout.X_AXIS));
        content.add(Box.createRigidArea(new Dimension(20, 10)));
        content.add(p1);
        content.add(Box.createRigidArea(new Dimension(20, 10)));

        this.setContentPane(content);
        this.pack();
        this.setTitle("Order Management");
        this.setSize(330, 250);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    /**
     * Metoda adauga actionListener pe butonul Go prin care se alege ce entitate se editeaza
     * @param al obiect de tip actionListener
     */
    public void addGoListener(ActionListener al) {
        btnGo.addActionListener(al);
    }

    /**
     * Getter pentru valoarea data de utilizator pe comboBox
     * @return valoarea din comboBox
     */
    public String getCombo() {
        return combo.getSelectedItem().toString();
    }

    /**
     * Metoda deschide o fereastra prin care transmite un mesaj de eroare utilizatorului
     * @param errMessage mesajul de eroare
     */
    void showError(String errMessage) {
        JOptionPane.showMessageDialog(this, errMessage);
    }

}
