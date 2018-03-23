package tvestergaard.lego.logic.building;

/**
 * Thrown when the {@link House} dimensions defined by some {@link HouseSpecification} cannot be built without errors.
 * <p>
 * The actual {@link Reason} for the {@link IllegalHouseDimensionsException} can be retrieved using the {@link IllegalHouseDimensionsException#getReason()}.
 */
public class IllegalHouseDimensionsException extends Exception
{

    /**
     * Reasons that the {@link IllegalHouseDimensionsException} can be raised.
     */
    public enum Reason
    {

        /**
         * Used when the width of the {@link HouseSpecification} is less than 4.
         */
        WIDTH_LESS_THAN_4,

        /**
         * Used when the depth of the {@link HouseSpecification} is less than 4.
         */
        DEPTH_LESS_THAN_4,

        /**
         * Used when the height of the {@link HouseSpecification} is less than 1 (zero height).
         */
        HEIGHT_LESS_THAN_1
    }

    /**
     * The {@link Reason} that the {@link IllegalHouseDimensionsException} was raised.
     */
    private final Reason reason;

    /**
     * The specification that caused the {@link IllegalHouseDimensionsException} to be raised.
     */
    private final HouseSpecification specification;

    /**
     * Creates a new {@link IllegalHouseDimensionsException}.
     *
     * @param message       The message provided to the exception.
     * @param reason        The {@link Reason} that the {@link IllegalHouseDimensionsException} was raised.
     * @param specification The specification that caused the {@link IllegalHouseDimensionsException} to be raised.
     */
    public IllegalHouseDimensionsException(String message, Reason reason, HouseSpecification specification)
    {
        super(message);
        this.reason = reason;
        this.specification = specification;
    }

    /**
     * Creates a new {@link IllegalHouseDimensionsException}.
     *
     * @param reason        The {@link Reason} that the {@link IllegalHouseDimensionsException} was raised.
     * @param specification The specification that caused the {@link IllegalHouseDimensionsException} to be raised.
     */
    public IllegalHouseDimensionsException(Reason reason, HouseSpecification specification)
    {
        this(reason.toString(), reason, specification);
    }

    /**
     * Returns the {@link Reason} that the {@link IllegalHouseDimensionsException} was raised.
     *
     * @return The {@link Reason} that the {@link IllegalHouseDimensionsException} was raised.
     */
    public Reason getReason()
    {
        return this.reason;
    }

    /**
     * Returns the specification that caused the {@link IllegalHouseDimensionsException} to be raised.
     *
     * @return The specification that caused the {@link IllegalHouseDimensionsException} to be raised.
     */
    public HouseSpecification getSpecification()
    {
        return this.specification;
    }
}
