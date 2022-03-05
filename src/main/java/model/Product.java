package model;

/**
 * Clasa care modeleaza entitatea product si are aceleasi campuri ca si tabelul din baza de date.
 *
 * @author Gorgan Raul-Alexandru
 * @since April 2021
 */
public class Product {

    private int id;
    private String name;
    private int price;
    private int stock;

    /**
     * Constructotul clasei, fara parametri, folosit in clasa AbstractDAO la extragerea la crearea obiectelor de tip produs
     */
    public Product(){

    }
    /**
     * Constructorul clasei, avand ca parametri toate variabilele instanta
     * @param id este id-ul produsului
     * @param name este numele produsului
     * @param price este pretul produsului
     * @param stock este stocul disponibil
     */
    public Product(int id, String name, int price, int stock) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Constructorul clasei, avand ca parametri toate variabilele instanta in afara de id.
     * Este folosit la apelarea operatiei de insert, deoarece id-ul se completeaza automat (incremental)
     * @param name este numele produsului
     * @param price este pretul produsului
     * @param stock este stocul disponibil
     */
    public Product(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    /**
     * Getter pentru id-ul produsului
     * @return id-ul produsului
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id-ul produsului
     * @param id este noul id
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pentru numele produsului
     * @return numele produsului
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pentru numele produsului
     * @param name noul nume
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pentru stocul produsului
     * @return stocul produsului
     */
    public int getStock() {
        return stock;
    }

    /**
     * Setter pentru stocul produsului
     * @param stock noul stoc
     */
    public void setStock(int stock) {
        this.stock = stock;
    }

    /**
     * Getter pentru pretul produsului
     * @return pretul produsului
     */
    public int getPrice() {
        return price;
    }

    /**
     * Setter pentru pretul produsului
     * @param price noul pret
     */
    public void setPrice(int price) {
        this.price = price;
    }

    public String[] getFieldValues() {
        String[] fieldValues = new String[4];
        fieldValues[0] = String.valueOf(id);
        fieldValues[1] = name;
        fieldValues[2] = String.valueOf(price);
        fieldValues[3] = String.valueOf(stock);
        return fieldValues;
    }

    /**
     * Metoda realizeaza o versiune afisabila a obiectului
     * @return un string cu obiectul de tip produs
     */
    @Override
    public String toString() {
        return "[" +
                "id= " + id +
                ", name= " + name +
                ", price= " + price +
                ", stock= " + stock +
                "]";
    }
}
