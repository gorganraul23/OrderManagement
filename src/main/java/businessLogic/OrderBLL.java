package businessLogic;

import Exceptions.NoSuchIdException;
import businessLogic.validators.QuantityValidator;
import businessLogic.validators.Validator;
import dataAccess.OrderDAO;
import model.Orders;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Order
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class OrderBLL {

    private List<Validator<Orders>> validators;
    private OrderDAO orderDAO;

    /**
     * Constructorul in care se adauga validatorii si se instantiaza obiectul de tip DAO
     */
    public OrderBLL() {
        validators = new ArrayList<>();
        validators.add(new QuantityValidator());

        orderDAO = new OrderDAO();
    }

    /**
     * Metoda apeleaza DAO pentru gasirea unei comenzi dupa id
     *
     * @param id este id-ul cautat
     * @return comanda cautata
     * @throws NoSuchIdException daca nu s-a gasit comanda in db
     */
    public Orders findById(int id) throws NoSuchIdException {
        Orders order = orderDAO.findById(id);
        if (order == null) {
            throw new NoSuchIdException("The order with id = " + id + " was not found!");
        }
        return order;
    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul Order.
     *
     * @return tablou cu comenzile
     */
    public String[][] findAll() {
        List<Orders> ordersList = orderDAO.findAll();
        if (ordersList.isEmpty()) {
            throw new NoSuchElementException("The table is empty!");
        }
        String[][] ordersArray = new String[ordersList.size()][];
        AtomicInteger numberOfOrders = new AtomicInteger();
        for (Orders order : ordersList) {
            ordersArray[numberOfOrders.getAndIncrement()] = order.getFieldValues();
        }
        return ordersArray;
    }

    /**
     * Metoda apeleaza DAO pentru a insera un comanda in bd
     *
     * @param order obiectul de inserat
     */
    public void insert(Orders order) {
        for (Validator<Orders> validator : validators) {
            validator.validate(order);
        }
        orderDAO.insert(order);
    }

    /**
     * Metoda apeleaza DAO pentru a face update unei comenzi din bd
     *
     * @param order obiectul de editat
     * @param field campul de editat
     * @param value noua valoare
     */
    public void update(Orders order, String field, String value) {
        Orders orderValidare;
        if (field.equals("quantity")) {
            orderValidare = new Orders(order.getId(), order.getClientID(), order.getProductID(), Integer.parseInt(value));
            for (Validator<Orders> validator : validators) {
                validator.validate(orderValidare);
            }
        }
        orderDAO.update(order, field, value);
    }

    /**
     * Metoda apeleaza DAO pentru a sterge o comanda din bd
     *
     * @param id este id-ul comenzii care se sterge
     */
    public void delete(int id) {
        orderDAO.delete(id);
    }

}
