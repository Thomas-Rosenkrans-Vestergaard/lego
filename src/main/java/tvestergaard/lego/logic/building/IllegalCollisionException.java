package tvestergaard.lego.logic.building;

/**
 * Thrown when the {@link Window} and {@link Door} 0defined by some {@link HouseSpecification} collide.
 */
public class IllegalCollisionException extends Exception
{

    /**
     * The specification that caused the {@link IllegalCollisionException} to be raised.
     */
    private final HouseSpecification specification;

    /**
     * Creates a new {@link IllegalCollisionException}.
     *
     * @param message       The message provided to the exception.
     * @param specification The specification that caused the {@link IllegalCollisionException} to be raised.
     */
    public IllegalCollisionException(String message, HouseSpecification specification)
    {
        super(message);
        this.specification = specification;
    }

    /**
     * Creates a new {@link IllegalCollisionException}.
     *
     * @param specification The specification that caused the {@link IllegalCollisionException} to be raised.
     */
    public IllegalCollisionException(HouseSpecification specification)
    {
        this("The provided window and door collides.", specification);
    }

    /**
     * Returns the specification that caused the {@link IllegalCollisionException} to be raised.
     *
     * @return The specification that caused the {@link IllegalCollisionException} to be raised.
     */
    public HouseSpecification getSpecification()
    {
        return this.specification;
    }
}
