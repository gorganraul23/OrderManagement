package Exceptions;

/**
 * Clasa exceptie care este aruncata cand se incearca editarea sau stergerea unei entitati care nu exista in baza de date
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class NoSuchIdException extends Exception{

    /**
     * Constructorul fara parametri
     */
    public NoSuchIdException(){

    }

    /**
     * Constructorul cu parametru
     * @param msg mesajul de eroare
     */
    public NoSuchIdException(String msg){
        super(msg);
    }

}
