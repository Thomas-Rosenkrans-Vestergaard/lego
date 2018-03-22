package tvestergaard.lego.logic.building;

public class InvalidWindowException extends Exception
{
    private final Window Window;

    /**
     * Constructs a new exception with the specified detail message.  The
     * cause is not initialized, and may subsequently be initialized by
     * a call to {@link #initCause}.
     *
     * @param message the detail message. The detail message is saved for
     *                later retrieval by the {@link #getMessage()} method.
     */
    public InvalidWindowException(String message, Window Window)
    {
        super(message);
        this.Window = Window;
    }

    public Window getWindow()
    {
        return this.Window;
    }
}
