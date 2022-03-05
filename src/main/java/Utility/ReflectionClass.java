package Utility;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

/**
 * Clasa care contine metoda prin care se extrag prin reflection header-ul si datele unui tabel din bd si se creaza JTable
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class ReflectionClass {

    /**
     * Metoda care formeaza tabelul
     *
     * @param modelObject    obiect model pentru header
     * @param businessObject obiect de logica pentru date
     * @return un JTable
     */
    public static JTable retrieveInformation(Object modelObject, Object businessObject) {
        String[] header = createHeader(modelObject);
        String[][] data = populateTable(businessObject);

        DefaultTableModel model = new DefaultTableModel(data, header);

        return new JTable(model);
    }

    /**
     * Metoda creeaza header-ul tabelului pe baza obiectului primit ca parametru
     *
     * @param t obiectul pentru care se face tabel
     * @return tablou cu campurile obiectului
     */
    public static String[] createHeader(Object t) {
        List<String> fields = new ArrayList<>();
        for (Field field : t.getClass().getDeclaredFields()) {
            fields.add(field.getName());
        }
        String[] headerTableFields = new String[fields.size()];
        fields.toArray(headerTableFields);
        return headerTableFields;
    }

    /**
     * Metoda extrage datele dintr-un tabel invocand metoda findAll
     *
     * @param t un obiect generic de logica
     * @return tablou cu datele
     */
    public static String[][] populateTable(Object t) {
        String[][] data = null;
        try {
            Method findAllMethod = t.getClass().getDeclaredMethod("findAll");
            data = (String[][]) findAllMethod.invoke(t);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return data;
    }

}
