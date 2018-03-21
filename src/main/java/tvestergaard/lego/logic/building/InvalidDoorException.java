package tvestergaard.lego.logic.building;

public class InvalidDoorException extends RuntimeException
{
    private final Door door;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidDoorException(String message, Door door)
    {
        super(message);
        this.door = door;
    }

    public Door getDoor()
    {
        return this.door;
    }
}
