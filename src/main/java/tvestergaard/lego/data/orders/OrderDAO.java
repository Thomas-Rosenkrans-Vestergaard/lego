package tvestergaard.lego.data.orders;

import tvestergaard.lego.data.members.Member;

import java.sql.SQLException;
import java.util.List;

/**
 * Data access object concerned with persisting orders to some data storage.
 */
public interface OrderDAO
{

    /**
     * Creates a new order record.
     *
     * @param builder The {@link OrderBuilder} containing the values to use when creating the new order record.
     * @return The {@link Order} instance representing the newly created record.
     * @throws SQLException
     */
    Order create(OrderBuilder builder) throws SQLException;

    /**
     * Selects the order record with the provided {@code id}.
     *
     * @param id The {@code id} of the order record to select.
     * @return The {@link Order} instance representing the selected record. Returns {@code null} if no such record exists.
     * @throws SQLException
     */
    Order select(int id) throws SQLException;

    /**
     * Selects all the order records.
     *
     * @return The list of {@link Order} instances representing the selected records.
     * @throws SQLException
     */
    List<Order> select() throws SQLException;

    /**
     * Selects all the order records by the provided {@link Member}.
     *
     * @param member The {@link Member} whose order records to select.
     * @return The list of {@link Order} instances representing the selected records.
     * @throws SQLException
     */
    List<Order> select(Member member) throws SQLException;
}
