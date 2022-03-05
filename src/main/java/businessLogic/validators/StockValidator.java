package businessLogic.validators;

import model.Product;

/**
 * Clasa care implementeaza metoda din interfata Validator pentru validarea stocului la operatia de insert product
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class StockValidator implements Validator<Product> {
    private static final int MIN_RANGE = 1;
    private static final int MAX_RANGE = 500;

    /**
     * Metoda care valideaza stocul
     * @param t produsul
     */
    public void validate(Product t) {

        if (t.getStock() < MIN_RANGE || t.getStock() > MAX_RANGE) {
            throw new IllegalArgumentException("The stock limits are not respected!");
        }
    }

}
