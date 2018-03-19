package tvestergaard.lego.logic;

import com.mysql.cj.jdbc.MysqlDataSource;

public class ProductionConnection
{
    private static MysqlDataSource source;

    public static MysqlDataSource source()
    {
        if (source == null) {
            source = new MysqlDataSource();
            source.setUser("lego");
            source.setDatabaseName("lego");
        }

        return source;
    }
}
