package Exceptions;

/**
 * Clasa exceptie care este aruncata cand se incearca plasarea unei comenzi cu cantitate mai mare decat stocul
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class UnderStockException extends Exception{

    /**
     * Constructorul fara parametri
     */
    public UnderStockException(){

    }

    /**
     * Constructorul cu parametru
     * @param msg mesajul de eroare
     */
    public UnderStockException(String msg){
        super(msg);
    }
}
