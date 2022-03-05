package businessLogic.validators;

import model.Product;

/**
 * Clasa care implementeaza metoda din interfata Validator pentru validarea pretului la operatia de insert product
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class PriceValidator implements Validator<Product> {
    private static final int MIN_RANGE = 1;

    /**
     * Metoda care valideaza pretul
     * @param t produsul
     */
    public void validate(Product t) {
        if (t.getPrice() < MIN_RANGE) {
            throw new IllegalArgumentException("The price limits are not respected!");
        }
    }
}
