package tvestergaard.lego.database.orders;

import tvestergaard.lego.database.members.Member;

import java.sql.SQLException;
import java.util.List;

public interface OrderDAO
{

    Order create(OrderBuilder builder) throws SQLException;

    Order select(int id) throws SQLException;

    Order select(Member member) throws SQLException;

    List<Order> select() throws SQLException;
}
