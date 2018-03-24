package tvestergaard.lego.database.orders;

public enum Status
{
    PLACED(0),
    SHIPPED(1);

    private final int code;

    Status(int code)
    {
        this.code = code;
    }

    private static Status[] values = Status.values();

    public static Status from(int code)
    {
        return values[code];
    }
}
