package dataAccess;

import connectToDB.ConnectionFactory;

import java.beans.IntrospectionException;
import java.beans.PropertyDescriptor;
import java.lang.reflect.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Clasa generica DAO pentru interactiunea directa cu baza de date
 *
 * @author: Gorgan Raul-Alexandru
 * @since: April 2021
 * @source http://www.java-blog.com/mapping-javaobjects-database-reflection-generics
 */
public abstract class AbstractDAO<T> {
    protected static final Logger LOGGER = Logger.getLogger(AbstractDAO.class.getName());

    private final Class<T> type;
    private final Query<T> query;

    /**
     * Constructorul in care se instantiaza clasa si obiectul de tip Query, unde se vor forma interogarile
     */
    @SuppressWarnings("unchecked")
    public AbstractDAO() {
        this.type = (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        query = new Query<>(type);
    }

    /**
     * Metoda prin care se extrag toate inregistrarile dintr-un tabel din bd
     * @return o lista cu obiectele
     */
    public List<T> findAll() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String findAllQuery = query.createFindAllQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findAllQuery);
            resultSet = statement.executeQuery();

            return createObjects(resultSet);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findAll " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda prin care se extrage obiectul cu id-ul dat ca parametru
     * @param id este id-ul obiectului de cautat
     * @return obiectul cautat
     */
    public T findById(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String findByIDQuery = query.createSelectQuery("id");
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(findByIDQuery);
            statement.setInt(1, id);
            resultSet = statement.executeQuery();

            return createObjects(resultSet).get(0);
        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:findById " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return null;
    }

    /**
     * Metoda apelata de findAll si findById prin care se face fetch obiectelor
     * @param resultSet inregistrarile rezultate in urma operatiei de findAll sau findById
     * @return o lista cu obiectele
     */
    private List<T> createObjects(ResultSet resultSet) {
        List<T> list = new ArrayList<T>();
        Constructor[] ctors = type.getDeclaredConstructors();
        Constructor ctor = null;
        for (int i = 0; i < ctors.length; i++) {
            ctor = ctors[i];
            if (ctor.getGenericParameterTypes().length == 0)
                break;
        }
        try {
            while (resultSet.next()) {
                ctor.setAccessible(true);
                T instance = (T) ctor.newInstance();
                for (Field field : type.getDeclaredFields()) {
                    String fieldName = field.getName();
                    Object value = resultSet.getObject(fieldName);
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(fieldName, type);
                    Method method = propertyDescriptor.getWriteMethod();
                    method.invoke(instance, value);
                }
                list.add(instance);
            }
        } catch (InstantiationException | IllegalAccessException | SecurityException | IllegalArgumentException | InvocationTargetException | SQLException | IntrospectionException e) {
            e.printStackTrace();
        }
        return list;
    }

    /**
     * Metoda folosita pentru a determina ultimul id introdus in tabel
     * @return id-ul maxim din tabel
     */
    protected int getLastId() {
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;
        String maxIDQuery = query.createMaxIDQuery();
        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(maxIDQuery);
            resultSet = statement.executeQuery();
            if (resultSet.next())
                return resultSet.getInt("max");

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, type.getName() + "DAO:maxID " + e.getMessage());
        } finally {
            ConnectionFactory.close(resultSet);
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return 0;
    }

    /**
     * Metoda prin care se insereaza un obiect in bd
     * @param t obiectul de inserat
     * @return obiectul t
     */
    public T insert(T t) {
        Connection connection = null;
        PreparedStatement statement = null;
        String insertQuery = query.createInsertQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(insertQuery);
            int fieldNumber = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                if (fieldNumber > 1) {
                    PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), t.getClass());
                    Method method = propertyDescriptor.getReadMethod();
                    String param = method.invoke(t).toString();
                    statement.setString(fieldNumber, param);
                } else {
                    statement.setInt(fieldNumber, getLastId() + 1);
                }
                fieldNumber++;
            }
            statement.executeUpdate();

        } catch (SQLException | IntrospectionException e) {
            LOGGER.log(Level.WARNING, t.getClass().getName() + "DAO:insert " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Metoda prin care se editeaza un obiect in bd
     * @param t obiectul de editat
     * @param fieldToModify campul de editat
     * @param value noua valoare
     * @return obiectul t
     */
    public T update(T t, String fieldToModify, String value) {
        Connection connection = null;
        PreparedStatement statement = null;
        String updateQuery = query.createUpdateQuery(t);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(updateQuery);
            int fieldNumber = 1;
            for (Field field : t.getClass().getDeclaredFields()) {
                PropertyDescriptor propertyDescriptor = new PropertyDescriptor(field.getName(), t.getClass());
                Method method = propertyDescriptor.getReadMethod();
                String param = method.invoke(t).toString();
                if (field.getName().equals(fieldToModify))
                    statement.setString(fieldNumber, value);
                else
                    statement.setString(fieldNumber, param);
                fieldNumber++;
            }
            PropertyDescriptor propertyDescriptor = new PropertyDescriptor("id", t.getClass());
            Method method = propertyDescriptor.getReadMethod();
            int id = (int) method.invoke(t);
            statement.setInt(fieldNumber, id);      //pt where id =?

            statement.executeUpdate();

        } catch (SQLException | IntrospectionException e) {
            LOGGER.log(Level.WARNING, t.getClass().getName() + "DAO:update " + e.getMessage());
        } catch (IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
        return t;
    }

    /**
     * Metoda prin care se sterge un obiect din bd
     * @param id este id-ul obiectului care se sterge
     */
    public void delete(int id) {
        Connection connection = null;
        PreparedStatement statement = null;
        String deleteQuery = query.createDeleteQuery(id);

        try {
            connection = ConnectionFactory.getConnection();
            statement = connection.prepareStatement(deleteQuery);
            statement.setInt(1, id);

            statement.executeUpdate();

        } catch (SQLException e) {
            LOGGER.log(Level.WARNING, "DAO:delete " + e.getMessage());
        } finally {
            ConnectionFactory.close(statement);
            ConnectionFactory.close(connection);
        }
    }
}
