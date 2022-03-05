package model;

/**
 * Clasa care modeleaza entitatea comanda si are aceleasi campuri ca si tabelul din baza de date.
 *
 * @author Gorgan Raul-Alexandru
 * @since April 2021
 */
public class Orders {

    private int id;
    private int clientID;
    private int productID;
    private int quantity;

    /**
     * Constructotul clasei, fara parametri, folosit in clasa AbstractDAO la extragerea la crearea obiectelor de tip comanda
     */
    public Orders(){

    }

    /**
     * Constructorul clasei, avand ca parametri toate variabilele instanta
     * @param id este id-ul comenzii
     * @param clientID este id-ul clientului
     * @param productID este id-ul produsului
     * @param quantity este cantitatea comenzii
     */
    public Orders(int id, int clientID, int productID, int quantity) {
        this.id = id;
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * Constructorul clasei, avand ca parametri toate variabilele instanta in afara de id.
     * Este folosit la apelarea operatiei de insert, deoarece id-ul se completeaza automat (incremental)
     * @param clientID este id-ul clientului
     * @param productID este id-ul produsului
     * @param quantity este cantitatea comenzii
     */
    public Orders(int clientID, int productID, int quantity) {
        this.clientID = clientID;
        this.productID = productID;
        this.quantity = quantity;
    }

    /**
     * Getter pentru id-ul comenzii
     * @return id-ul comenzii
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id-ul comenzii
     * @param id este noul id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pentru id-ul clientului
     * @return id-ul clientului
     */
    public int getClientID() {
        return clientID;
    }

    /**
     * Setter pentru id-ul clientului
     * @param clientID noul id al clientului
     */
    public void setClientID(int clientID) {
        this.clientID = clientID;
    }

    /**
     * Getter pentru id-ul produsului
     * @return id-ul produsului
     */
    public int getProductID() {
        return productID;
    }

    /**
     * Setter pentru id-ul produsului
     * @param productID noul id al produsului
     */
    public void setProductID(int productID) {
        this.productID = productID;
    }

    /**
     * Getter pentru cantitatea comenzii
     * @return cantitatea comenzii
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Setter pentru cantitatea comenzii
     * @param quantity noua cantitate
     */
    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String[] getFieldValues() {
        String[] fieldValues = new String[4];
        fieldValues[0] = String.valueOf(id);
        fieldValues[1] = String.valueOf(clientID);
        fieldValues[2] = String.valueOf(productID);
        fieldValues[3] = String.valueOf(quantity);
        return fieldValues;
    }

    /**
     * Metoda realizeaza o versiune afisabila a obiectului
     * @return un string cu obiectul de tip comanda
     */
    public String toString(){
        return "[id= " + id +
               ", clientID= " + clientID +
               ", productID= " + productID +
               ", quantity= " + quantity +
               "]";
    }
}
