package tvestergaard.lego.logic.building;

/**
 * Thrown when the {@link Window} defined by some {@link HouseSpecification} cannot be built without errors.
 * <p>
 * The actual {@link Reason} for the {@link IllegalWindowException} can be retrieved using the {@link IllegalWindowException#getReason()}.
 */
public class IllegalWindowException extends Exception
{

    /**
     * Reasons that the {@link IllegalWindowException} can be raised.
     */
    public enum Reason
    {

        /**
         * Used when the y-position of the {@link Window} is less than one.
         */
        POSITION_Y_LESS_THAN_1,

        /**
         * Used when the placement of the {@link Window} extends above the wall on which it is placed.
         */
        PLACEMENT_OUT_OF_BOUNDS_TOP,

        /**
         * Used when the placement of the {@link Window} extends beyond the left side of the wall on which it is placed.
         */
        PLACEMENT_OUT_OF_BOUNDS_LEFT,

        /**
         * Used when the placement of the {@link Window} extends beyond the right side of the wall on which it is placed.
         */
        PLACEMENT_OUT_OF_BOUNDS_RIGHT,

        /**
         * Used when the {@link Window} has no width.
         */
        DIMENSIONS_WIDTH_LESS_THAN_1,

        /**
         * Used when the {@link Window} has no height.
         */
        DIMENSIONS_HEIGHT_LESS_THAN_1
    }

    /**
     * The {@link Reason} that the {@link IllegalWindowException} was raised.
     */
    private final Reason reason;

    /**
     * The specification that caused the {@link IllegalWindowException} to be raised.
     */
    private final HouseSpecification specification;

    /**
     * Creates a new {@link IllegalWindowException}.
     *
     * @param message       The message of the exception.
     * @param reason        The {@link Reason} that caused the {@link IllegalWindowException} was raised.
     * @param specification The specification that caused the {@link IllegalWindowException} was raised.
     */
    public IllegalWindowException(String message, Reason reason, HouseSpecification specification)
    {
        super(message);
        this.specification = specification;
        this.reason = reason;
    }


    /**
     * Creates a new {@link IllegalWindowException}.
     *
     * @param reason        The {@link Reason} that caused the {@link IllegalWindowException} was raised.
     * @param specification The specification that caused the {@link IllegalWindowException} was raised.
     */
    public IllegalWindowException(Reason reason, HouseSpecification specification)
    {
        this(reason.toString(), reason, specification);
    }

    /**
     * Returns the {@link Reason} that caused the {@link IllegalWindowException} was raised.
     *
     * @return The {@link Reason} that caused the {@link IllegalWindowException} was raised.
     */
    public Reason getReason()
    {
        return this.reason;
    }

    /**
     * Returns the specification that caused the {@link IllegalWindowException} was raised.
     *
     * @return The specification that caused the {@link IllegalWindowException} was raised.
     */
    public HouseSpecification getSpecification()
    {
        return this.specification;
    }
}
