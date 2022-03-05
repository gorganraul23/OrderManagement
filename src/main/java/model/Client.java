package model;

/**
 * Clasa care modeleaza entitatea client si are aceleasi campuri ca si tabelul din baza de date.
 *
 * @author Gorgan Raul-Alexandru
 * @since April 2021
 */
public class Client {

    private int id;
    private String name;
    private String address;
    private String email;

    /**
     * Constructotul clasei, fara parametri, folosit in clasa AbstractDAO la extragerea la crearea obiectelor de tip client
     */
    public Client(){

    }

    /**
     * Constructorul clasei, avand ca parametri toate variabilele instanta in afara de id.
     * Este folosit la apelarea operatiei de insert, deoarece id-ul se completeaza automat (incremental)
     * @param name este numele clientului
     * @param address este adresa clientului
     * @param email este adresa de email a clientului
     */
    public Client(String name, String address, String email) {
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Constructorul clasei, avand toate variabilele instanta.
     * @param id este id-ul clientului
     * @param name este numele clientului
     * @param address este adresa clientului
     * @param email este adresa de email a clientului
     */
    public Client(int id, String name, String address, String email) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
    }

    /**
     * Getter pentru id-ul clientului
     * @return id-ul clientului
     */
    public int getId() {
        return id;
    }

    /**
     * Setter pentru id-ul clientului
     * @param id noul id al clientului
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * Getter pentru numele clientului
     * @return numele clientului
     */
    public String getName() {
        return name;
    }

    /**
     * Setter pentru numele clientului
     * @param name noul nume al clientului
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Getter pentru adresa clientului
     * @return adresa clientului
     */
    public String getAddress() {
        return address;
    }

    /**
     * Setter pentru adresa clientului
     * @param address noua adresa a clientului
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Getter pentru email-ul clientului
     * @return email-ul clientului
     */
    public String getEmail() {
        return email;
    }

    /**
     * Setter pentru email-ul clientului
     * @param email noul email al clientului
     */
    public void setEmail(String email) {
        this.email = email;
    }

    public String[] getFieldValues() {
        String[] fieldValues = new String[4];
        fieldValues[0] = String.valueOf(id);
        fieldValues[1] = name;
        fieldValues[2] = address;
        fieldValues[3] = email;
        return fieldValues;
    }

    /**
     * Metoda realizeaza o versiune afisabila a obiectului
     * @return un string cu obiectul de tip client
     */
    @Override
    public String toString() {
        return "[" +
                "id= " + id +
                ", name= " + name +
                ", address= " + address +
                ", email= " + email +
                "]";
    }
}
