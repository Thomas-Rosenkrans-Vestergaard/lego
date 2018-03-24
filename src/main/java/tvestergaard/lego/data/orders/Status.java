package tvestergaard.lego.data.orders;

/**
 * Represents the status of some {@link Order}.
 */
public enum Status
{
    PLACED(0),
    SHIPPED(1);

    /**
     * The code representing the {@link Status}.
     */
    private final int code;

    /**
     * Creates a new {@link Status}.
     *
     * @param code The code representing the {@link Status}.
     */
    Status(int code)
    {
        this.code = code;
    }

    /**
     * Returns the code representing the {@link Status}.
     *
     * @return The code representing the {@link Status}.
     */
    public int getCode()
    {
        return this.code;
    }

    /**
     * The possible {@link Status} values.
     */
    private static Status[] values = Status.values();

    /**
     * Returns the {@link Status} value with the provided {@code code}.
     *
     * @param code The {@code code} of the {@link Status} to return.
     * @return The {@link Status} value with the provided {@code code}.
     */
    public static Status from(int code)
    {
        return values[code];
    }
}
