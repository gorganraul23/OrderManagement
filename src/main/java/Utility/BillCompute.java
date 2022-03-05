package Utility;

import model.Client;
import model.Product;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clasa realizeaza factura unei comenzi. Creaza un fisier txt cu datele necesare
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class BillCompute {

    /**
     * Metoda prin care se creaza factura unei comenzi
     *
     * @param client   clientul care efectueaza comanda
     * @param product  produsul comandat
     * @param quantity cantitatea in care se comanda
     * @throws IOException exceptie pentru fisier
     */
    public static void createBill(Client client, Product product, int quantity) throws IOException {
        File file = new File("order_bill_" + client.getId() + "_" + product.getId() + ".txt");
        FileWriter fw = null;
        try {
            fw = new FileWriter(file.getAbsoluteFile());
        } catch (IOException e) {
        }
        BufferedWriter bw = new BufferedWriter(fw);

        bw.write("### Bill for order " + client.getId() + "_" + product.getId() + " ###\n\n");
        bw.write("# Client details:\n Name: " + client.getName() +
                "\n Address: " + client.getAddress() + "\n\n");
        bw.write("# Products:\n " + quantity + " x " + product.getName() +
                " .......... " + quantity + " x " + product.getPrice() + "\n\n\t\t" +
                "TOTAL: " + quantity * product.getPrice() + "\n\n" +
                "################################");
        try {
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
