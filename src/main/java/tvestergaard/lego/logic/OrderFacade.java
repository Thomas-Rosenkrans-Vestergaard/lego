package tvestergaard.lego.logic;

import tvestergaard.lego.data.members.Member;
import tvestergaard.lego.data.orders.MysqlOrderDAO;
import tvestergaard.lego.data.orders.Order;
import tvestergaard.lego.data.orders.OrderBuilder;

import java.util.List;

public class OrderFacade
{

    private final MysqlOrderDAO dao;

    public OrderFacade(MysqlOrderDAO dao)
    {
        this.dao = dao;
    }

    public OrderFacade()
    {
        this(new MysqlOrderDAO(ProductionConnection.source()));
    }

    public Order create(OrderBuilder builder) throws ApplicationException
    {
        try {
            return dao.create(builder);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public Order select(int id) throws ApplicationException
    {
        try {
            return dao.select(id);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public List<Order> select(Member member) throws ApplicationException
    {
        try {
            return dao.select(member);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public List<Order> select() throws ApplicationException
    {
        try {
            return dao.select();
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }

    public Order update(int id, OrderBuilder builder) throws ApplicationException
    {
        try {
            return dao.update(id, builder);
        } catch (Exception e) {
            throw new ApplicationException(e);
        }
    }
}
