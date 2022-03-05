package businessLogic;

import Exceptions.NoSuchIdException;
import businessLogic.validators.PriceValidator;
import businessLogic.validators.StockValidator;
import businessLogic.validators.Validator;
import dataAccess.ProductDAO;
import model.Product;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Clasa care se ocupa de logica aplicatiei pentru modelul Product
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class ProductBLL {

    private List<Validator<Product>> validators;
    private ProductDAO productDAO;

    /**
     * Constructorul in care se adauga validatorii si se instantiaza obiectul de tip DAO
     */
    public ProductBLL() {
        validators = new ArrayList<>();
        validators.add(new PriceValidator());
        validators.add(new StockValidator());

        productDAO = new ProductDAO();
    }

    /**
     * Metoda apeleaza DAO pentru gasirea unui produs dupa id
     *
     * @param id este id-ul cautat
     * @return produsul cautat
     * @throws NoSuchIdException daca nu s-a gasit produsul in db
     */
    public Product findById(int id) throws NoSuchIdException {
        Product product = productDAO.findById(id);
        if (product == null) {
            throw new NoSuchIdException("The product with id = " + id + " was not found!");
        }
        return product;
    }

    /**
     * Metoda apeleaza DAO pentru a gasi toate inregistrarile din tabelul Product.
     *
     * @return tablou cu produsele
     */
    public String[][] findAll() {
        List<Product> productsList = productDAO.findAll();
        if (productsList.isEmpty()) {
            throw new NoSuchElementException("The table is empty!");
        }
        String[][] productsArray = new String[productsList.size()][];
        AtomicInteger numberOfProducts = new AtomicInteger();
        for (Product product : productsList) {
            productsArray[numberOfProducts.getAndIncrement()] = product.getFieldValues();
        }
        return productsArray;
    }

    /**
     * Metoda apeleaza DAO pentru a insera un produsul in bd
     *
     * @param product obiectul de inserat
     */
    public void insert(Product product) {
        for (Validator<Product> validator : validators) {
            validator.validate(product);
        }
        productDAO.insert(product);
    }

    /**
     * Metoda apeleaza DAO pentru a face update unui produs din bd
     *
     * @param product obiectul de editat
     * @param field   campul de editat
     * @param value   noua valoare
     */
    public void update(Product product, String field, String value) {
        Product productValidare = null;
        if (field.equals("price")) {
            productValidare = new Product(product.getId(), product.getName(), Integer.parseInt(value), product.getStock());
        } else if (field.equals("stock")) {
            productValidare = new Product(product.getId(), product.getName(), product.getPrice(), Integer.parseInt(value));
        }
        for (Validator<Product> validator : validators) {
            validator.validate(productValidare);
        }

        productDAO.update(product, field, value);
    }

    /**
     * Metoda apeleaza DAO pentru a sterge un produs din bd
     *
     * @param id este id-ul produsului care se sterge
     */
    public void delete(int id) {
        productDAO.delete(id);
    }

}
