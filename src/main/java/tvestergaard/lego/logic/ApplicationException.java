package tvestergaard.lego.logic;

/**
 * General application exception.
 */
public class ApplicationException extends RuntimeException
{

    /**
     * Creates a new {@link ApplicationException}.
     *
     * @param cause The cause of the exception.
     */
    public ApplicationException(Throwable cause)
    {
        super(cause);
    }
}
