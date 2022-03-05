package businessLogic;

import Exceptions.NoSuchIdException;
import businessLogic.validators.EmailValidator;
import businessLogic.validators.Validator;
import dataAccess.ClientDAO;
import model.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Client
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class ClientBLL {

    private List<Validator<Client>> validators;
    private ClientDAO clientDAO;

    /**
     * Constructorul in care se adauga validatorii si se instantiaza obiectul de tip DAO
     */
    public ClientBLL() {
        validators = new ArrayList<>();
        validators.add(new EmailValidator());

        clientDAO = new ClientDAO();
    }

    /**
     * Metoda apeleaza DAO pentru gasirea unui client dupa id
     *
     * @param id este id-ul cautat
     * @return clientul cautat
     * @throws NoSuchIdException daca nu s-a gasit clientul in db
     */
    public Client findById(int id) throws NoSuchIdException {
        Client client = clientDAO.findById(id);
        if (client == null) {
            throw new NoSuchIdException("The client with id = " + id + " was not found!");
        }
        return client;
    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul Client.
     *
     * @return un array cu toti clientii
     */
    public String[][] findAll() {
        List<Client> clientsList = clientDAO.findAll();
        if (clientsList.isEmpty()) {
            throw new NoSuchElementException("The table is empty!");
        }
        String[][] clientsArray = new String[clientsList.size()][];
        AtomicInteger numberOfClients = new AtomicInteger();
        for (Client client : clientsList) {
            clientsArray[numberOfClients.getAndIncrement()] = client.getFieldValues();
        }
        return clientsArray;
    }

    /**
     * Metoda apeleaza DAO pentru a insera un client in bd
     *
     * @param client obiectul de inserat
     */
    public void insert(Client client) {

        for (Validator<Client> validator : validators) {
            validator.validate(client);
        }
        clientDAO.insert(client);
    }

    /**
     * Metoda apeleaza DAO pentru a face update unui client din bd
     *
     * @param client obiectul de editat
     * @param field  campul de editat
     * @param value  noua valoare
     */
    public void update(Client client, String field, String value) {
        Client clientValidare = null;
        if (field.equals("email")) {
            clientValidare = new Client(client.getId(), client.getName(), client.getAddress(), value);
            for (Validator<Client> validator : validators) {
                validator.validate(clientValidare);
            }
        }
        clientDAO.update(client, field, value);
    }

    /**
     * Metoda apeleaza DAO pentru a sterge un client din bd
     *
     * @param id este id-ul clientului care se sterge
     */
    public void delete(int id) {
        clientDAO.delete(id);
    }

}
