package tvestergaard.lego.logic.building;

import java.util.Arrays;
import java.util.List;

public class BricklayerException extends Exception
{

    /**
     * The possible reasons that a {@link BricklayerException} was throws.
     */
    public enum Reason
    {

        /**
         * Used when the width of the provided {@link House} is less than 4.
         */
        WIDTH_LESS_THAN_4,

        /**
         * Used when the depth of the provided {@link House} is less than 4.
         */
        DEPTH_LESS_THAN_4,

        /**
         * Used when the height of the provided {@link House} is less than 1 (zero height).
         */
        HEIGHT_LESS_THAN_1,

        /**
         * Used when the y-coordinate of the position of the provided {@link Door} is not zero.
         */
        DOOR_POSITION_Y_NOT_ZERO,

        /**
         * Used when the provided {@link Door} is out of bounds on the left side of the wall.
         */
        DOOR_OUT_OF_BOUNDS_LEFT,

        /**
         * Used when the provided {@link Door} is out of bounds on the right side of the wall.
         */
        DOOR_OUT_OF_BOUNDS_RIGHT,

        /**
         * Used when the provided {@link Door} is out of bounds on the top of the wall.
         */
        DOOR_OUT_OF_BOUNDS_TOP,

        /**
         * Used when the width of the provided {@link Door} was less than one.
         */
        DOOR_WIDTH_LESS_THAN_1,

        /**
         * Used when the height of the provided {@link Door} was less than one.
         */
        DOOR_HEIGHT_LESS_THAN_1,

        /**
         * Used when the specified {@link Door} and {@link Window} collide.
         */
        DOOR_WINDOW_COLLISION,

        /**
         * Used when the y-position of the {@link Window} is less than one.
         */
        WINDOW_POSITION_Y_LESS_THAN_1,

        /**
         * Used when the placement of the {@link Window} extends above the wall on which it is placed.
         */
        WINDOW_OUT_OF_BOUNDS_TOP,

        /**
         * Used when the placement of the {@link Window} extends beyond the left side of the wall on which it is placed.
         */
        WINDOW_OUT_OF_BOUNDS_LEFT,

        /**
         * Used when the placement of the {@link Window} extends beyond the right side of the wall on which it is placed.
         */
        WINDOW_OUT_OF_BOUNDS_RIGHT,

        /**
         * Used when the {@link Window} has no width.
         */
        WINDOW_WIDTH_LESS_THAN_1,

        /**
         * Used when the {@link Window} has no height.
         */
        WINDOW_HEIGHT_LESS_THAN_1,
    }

    /**
     * The {@link HouseSpecification} that caused the {@link BricklayerException} to be thrown.
     */
    private final HouseSpecification specification;

    /**
     * The {@link Reason}s why the {@link BricklayerException} was thrown. Must contain at least one {@link Reason}.
     */
    private final List<Reason> reasons;

    /**
     * Creates a new {@link BricklayerException} with one {@link Reason}.
     *
     * @param specification The {@link HouseSpecification} that caused the {@link BricklayerException} to be thrown.
     * @param reason        The {@link Reason} why the {@link BricklayerException} was thrown.
     */
    public BricklayerException(HouseSpecification specification, Reason reason)
    {
        this(specification, Arrays.asList(reason));
    }

    /**
     * Creates a new {@link BricklayerException} with one {@link Reason}.
     *
     * @param specification The {@link HouseSpecification} that caused the {@link BricklayerException} to be thrown.
     * @param reasons       The {@link Reason}s why the {@link BricklayerException} was thrown. Must contain at least
     *                      one {@link Reason}.
     */
    public BricklayerException(HouseSpecification specification, List<Reason> reasons)
    {
        super("Reasons: " + createReasonString(reasons));

        if (reasons.isEmpty())
            throw new IllegalArgumentException("Must contain at least one reason.");

        this.specification = specification;
        this.reasons = reasons;
    }

    /**
     * Creates a comma separated string of the names of the provided {@link Reason}s.
     *
     * @param reasons The {@link Reason}s to turn into a comma separated string.
     * @return The resulting string.
     */
    private static String createReasonString(List<Reason> reasons)
    {
        StringBuilder builder = new StringBuilder();
        for (Reason reason : reasons) {
            builder.append(reason);
            builder.append(',');
            builder.append(' ');
        }

        return builder.toString();
    }

    /**
     * Returns the {@link HouseSpecification} that caused the {@link BricklayerException} to be thrown.
     *
     * @return The {@link HouseSpecification} that caused the {@link BricklayerException} to be thrown.
     */
    public HouseSpecification getSpecification()
    {
        return this.specification;
    }

    /**
     * Returns the {@link Reason}s why the {@link BricklayerException} was thrown. Must contain at least one {@link Reason}.
     *
     * @return The {@link Reason}s why the {@link BricklayerException} was thrown. Must contain at least one {@link Reason}.
     */
    public List<Reason> getReasons()
    {
        return this.reasons;
    }
}
