package businessLogic.validators;

import model.Orders;


/**
 * Clasa care implementeaza metoda din interfata Validator pentru validarea cantitatii la operatia de insert order
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class QuantityValidator implements Validator<Orders> {
    private static final int MIN_RANGE = 1;

    /**
     * Metoda care valideaza cantitatea
     * @param t comanda
     */
    public void validate(Orders t) {
        if (t.getQuantity() < MIN_RANGE) {
            throw new IllegalArgumentException("The quantity limits are not respected!");
        }
    }
}
