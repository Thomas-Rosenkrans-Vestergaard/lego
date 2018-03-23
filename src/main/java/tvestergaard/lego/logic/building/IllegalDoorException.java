package tvestergaard.lego.logic.building;

/**
 * Thrown when the {@link Door} defined by some {@link HouseSpecification} cannot be built without errors.
 */
public class IllegalDoorException extends Exception
{

    /**
     * Reasons that the {@link IllegalDoorException} can be raised.
     */
    public enum Reason
    {

        /**
         * The y-coordinate of the position of the provided {@link Door} is not zero.
         */
        POSITION_Y_NOT_ZERO,

        /**
         * The provided {@link Door} is out of bounds on the left side of the wall.
         */
        PLACEMENT_OUT_OF_BOUNDS_LEFT,

        /**
         * The provided {@link Door} is out of bounds on the right side of the wall.
         */
        PLACEMENT_OUT_OF_BOUNDS_RIGHT,

        /**
         * The provided {@link Door} is out of bounds on the top of the wall.
         */
        PLACEMENT_OUT_OF_BOUNDS_TOP,

        /**
         * The width of the provided {@link Door} was less than one.
         */
        DIMENSIONS_WIDTH_LESS_THAN_1,

        /**
         * The height of the provided {@link Door} was less than one.
         */
        DIMENSIONS_HEIGHT_LESS_THAN_1
    }

    /**
     * The {@link Reason} that the {@link IllegalDoorException} was thrown.
     */
    private final Reason reason;

    /**
     * The specification that caused the {@link IllegalDoorException} to be raised.
     */
    private final HouseSpecification specification;

    /**
     * Creates a new {@link IllegalDoorException}.
     *
     * @param message       The message provided to the exception.
     * @param reason        The {@link Reason} that the {@link IllegalDoorException} was thrown.
     * @param specification The specification that caused the {@link IllegalDoorException} to be raised.
     */
    public IllegalDoorException(String message, Reason reason, HouseSpecification specification)
    {
        super(message);
        this.reason = reason;
        this.specification = specification;
    }

    /**
     * Creates a new {@link IllegalDoorException}.
     *
     * @param reason        The {@link Reason} that the {@link IllegalDoorException} was thrown.
     * @param specification The specification that caused the {@link IllegalDoorException} to be raised.
     */
    public IllegalDoorException(Reason reason, HouseSpecification specification)
    {
        this(reason.toString(), reason, specification);
    }

    /**
     * Returns the {@link Reason} that the {@link IllegalDoorException} was thrown.
     *
     * @return The {@link Reason} that the {@link IllegalDoorException} was thrown.
     */
    public Reason getReason()
    {
        return this.reason;
    }

    /**
     * Returns the specification that caused the {@link IllegalDoorException} to be raised.
     *
     * @return The specification that caused the {@link IllegalDoorException} to be raised.
     */
    public HouseSpecification getSpecification()
    {
        return this.specification;
    }
}
