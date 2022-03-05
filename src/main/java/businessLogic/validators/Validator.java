package businessLogic.validators;

/**
 * Interfata pentru clasele care vor implementa validatorii
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public interface Validator<T> {

    /**
     * Metoda care trebuie implementata
     * @param t obiect client/product/order
     */
    public void validate(T t);
}
