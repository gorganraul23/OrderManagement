package dataAccess;

import java.lang.reflect.Field;

/**
 * Clasa contine metode prin care se construiesc interogarile pentru bd
 *
 * @param <T> obiectul de tip T
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 */
public class Query<T> {

    private Class<T> type;

    /**
     * Constructorul in care se asigneaza tipul
     *
     * @param type tipul clasei
     */
    public Query(Class<T> type) {
        this.type = type;
    }

    /**
     * Metoda creeaza un select query
     *
     * @param field field-ul dupa care se cauta
     * @return string cu select query
     */
    public String createSelectQuery(String field) {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(type.getSimpleName()).append(" WHERE ").append(field).append(" = ?");

        return sb.toString();
    }

    /**
     * Metoda construieste un select * query
     *
     * @return string cu select *
     */
    public String createFindAllQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT * FROM ").append(type.getSimpleName());

        return sb.toString();
    }

    /**
     * Metoda construieste un insert query
     *
     * @param t obiectul de inserat
     * @return string cu insert query
     */
    public String createInsertQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO ").append(t.getClass().getSimpleName()).append(" VALUES(");
        int numberOfFields = t.getClass().getDeclaredFields().length;
        sb.append("?,".repeat(Math.max(0, numberOfFields - 1)));
        sb.append("?)");

        return sb.toString();
    }

    /**
     * Metoda construieste un update query
     *
     * @param t obiectul de editat
     * @return string cu update query
     */
    public String createUpdateQuery(T t) {
        StringBuilder sb = new StringBuilder();
        sb.append("UPDATE ").append(type.getSimpleName()).append(" SET ");
        for (Field field : t.getClass().getDeclaredFields()) {
            sb.append(field.getName()).append("=?,");
        }
        sb.deleteCharAt(sb.length() - 1);
        sb.append(" WHERE id =?");
        return sb.toString();
    }

    /**
     * Metoda construieste un delete query
     * @param id este id-ul obiectului care se sterge
     * @return string cu delete query
     */
    public String createDeleteQuery(int id) {
        StringBuilder sb = new StringBuilder();
        sb.append("DELETE FROM ").append(type.getSimpleName()).append(" WHERE id= ?;");
        return sb.toString();
    }

    /**
     * Metoda construieste un select max query
     * @return string cu select max
     */
    public String createMaxIDQuery() {
        StringBuilder sb = new StringBuilder();
        sb.append("SELECT MAX(id) AS max FROM ").append(type.getSimpleName());

        return sb.toString();
    }

}
