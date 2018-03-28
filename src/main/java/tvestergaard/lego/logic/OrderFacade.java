package tvestergaard.lego.logic;

import tvestergaard.lego.data.members.Member;
import tvestergaard.lego.data.orders.Order;
import tvestergaard.lego.data.orders.OrderBuilder;
import tvestergaard.lego.data.orders.OrderDAO;

import java.util.List;

/**
 * Provides a simplified interface of {@link OrderDAO} for querying persistent storage for orders.
 */
public class OrderFacade
{


    /**
     * The {@link OrderDAO} for querying persistent storage for orders.
     */
    private final OrderDAO dao;

    /**
     * Creates a new {@link OrderDAO}.
     *
     * @param dao The {@link OrderDAO} for querying persistent storage for orders.
     */
    public OrderFacade(OrderDAO dao)
    {
        this.dao = dao;
    }

    /**
     * Inserts a new order into persistent storage based on the provided {@link OrderBuilder}.
     *
     * @param builder The {@link OrderBuilder} containing the information about the new order record.
     * @return The {@link Order} instance representing the newly created record.
     */
    public Order create(OrderBuilder builder)
    {
        try {
            return dao.create(builder);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Retrieves the order record with the provided {@code id}.
     *
     * @param id The id of the record to retrieve.
     * @return The {@link Order} instance representing the retrieved record.
     */
    public Order select(int id)
    {
        try {
            return dao.select(id);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Retrieves all the order records placed by the provided {@link Member}.
     *
     * @param member The member to retrieve the orders records of.
     * @return The list of {@link Order}s placed by the provided {@link Member}.
     */
    public List<Order> select(Member member)
    {
        try {
            return dao.select(member);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Retrieves all the order records from persistent storage.
     *
     * @return The list of {@link Order} instances representing the retrieved records.
     */
    public List<Order> select()
    {
        try {
            return dao.select();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    /**
     * Updates the order record with the provided {@code id} using the provided {@link OrderBuilder}.
     *
     * @param id      The id of the order record to update.
     * @param builder The information used during the update.
     * @return The {@link Order} record representing the updated order record.
     */
    public Order update(int id, OrderBuilder builder)
    {
        try {
            return dao.update(id, builder);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
