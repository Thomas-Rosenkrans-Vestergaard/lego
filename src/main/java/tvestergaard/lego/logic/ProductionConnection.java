package tvestergaard.lego.logic;

import com.mysql.cj.jdbc.MysqlDataSource;
import tvestergaard.lego.database.members.MemberDAO;
import tvestergaard.lego.database.members.MysqlMemberDAO;

public class ProductionConnection
{
    private static MysqlDataSource source;
    private static MemberDAO       memberDAO;

    public static MysqlDataSource source()
    {
        if (source == null) {
            source = new MysqlDataSource();
            source.setUser("lego");
            source.setDatabaseName("lego");
        }

        return source;
    }

    public static MemberDAO memberDAO()
    {
        if (memberDAO == null) {
            memberDAO = new MysqlMemberDAO(source());
        }

        return memberDAO;
    }
}
