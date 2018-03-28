package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Cube;
import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.logic.geometry.PositionedSquare;

import java.util.ArrayList;
import java.util.List;

import static tvestergaard.lego.logic.building.BricklayerException.Reason;
import static tvestergaard.lego.logic.building.BricklayerException.Reason.*;

/**
 * {@link Bricklayer} implementation building {@link House} instances from {@link HouseSpecification} instances using
 * the "Stretching bond" brick pattern.
 *
 * @see <a href="https://www.theconstructioncivil.org/types-of-brick-bonds/">Brick bonds</a>
 */
public class HalfOffsetBricklayer implements Bricklayer
{

    private static final int BRICK_LARGE  = 4;
    private static final int BRICK_MEDIUM = 2;
    private static final int BRICK_SMALL  = 1;
    private static final int BRICK_WIDTH  = 2;

    /**
     * The offset of the brick currently being placed, from the 4-brick pattern. When the currentOffset is 2, a 2x2 brick
     * should be placed as soon as possible to keep the 4-brick pattern.
     */
    private int currentOffset = 0;

    /**
     * Creates the {@link House} specified from the provided {@link HouseSpecification}.
     *
     * @param specifications The specifications for the {@link House} to build.
     * @return The {@link House} created from the provided {@link HouseSpecification}.
     * @throws BricklayerException When the provided {@link HouseSpecification} cannot be built.
     */
    @Override
    public House lay(HouseSpecification specifications) throws BricklayerException
    {
        validateSpecifications(specifications);

        Wall front = buildWall(specifications, Side.FRONT);
        Wall back  = buildWall(specifications, Side.BACK);
        Wall right = buildWall(specifications, Side.RIGHT);
        Wall left  = buildWall(specifications, Side.LEFT);

        return new House(
                specifications.dimensions.width,
                specifications.dimensions.height,
                specifications.dimensions.depth,
                specifications.door,
                specifications.window, front, back, right, left);
    }

    /**
     * Validates the provided {@link HouseSpecification}.
     *
     * @param specification The {@link HouseSpecification} to validate the properties of.
     * @throws BricklayerException When the provided {@link HouseSpecification} is not valid.
     */
    public void validateSpecifications(HouseSpecification specification) throws BricklayerException
    {

        List<Reason> reasons = new ArrayList<>();

        // Check minimum house dimensions
        Cube dimensions = specification.getDimensions();
        if (dimensions.width < 4)
            reasons.add(WIDTH_LESS_THAN_4);
        if (dimensions.height < 1)
            reasons.add(HEIGHT_LESS_THAN_1);
        if (dimensions.depth < 4)
            reasons.add(DEPTH_LESS_THAN_4);

        // When present, validate door
        if (specification.door != null)
            validateDoor(specification, reasons);

        // When present, validate window
        if (specification.window != null)
            validateWindow(specification, reasons);

        // When present, check that the window and door does not overlap each other.
        if (specification.window != null && specification.dimensions != null)
            if (specification.window.overlaps(specification.door))
                reasons.add(DOOR_WINDOW_COLLISION);

        // Throw an exception if any reasons for failure were found
        if (!reasons.isEmpty()) {
            throw new BricklayerException(specification, reasons);
        }
    }

    /**
     * Validates the {@link Door} specified in the provided {@link HouseSpecification}.
     *
     * @param specification The {@link HouseSpecification} to validate the {@link Door} of.
     * @param reasons       The list of {@link Reason}s why the validation would fail. This list is filled with {@link Reason}s
     *                      why the {@link Door} is not valid.
     */
    private void validateDoor(HouseSpecification specification, List<Reason> reasons)
    {
        Cube houseDimensions = specification.dimensions;
        Door door            = specification.door;

        if (door.y != 0)
            reasons.add(DOOR_POSITION_Y_NOT_ZERO);

        if (door.width < 1)
            reasons.add(DOOR_WIDTH_LESS_THAN_1);

        if (door.height < 1)
            reasons.add(DOOR_HEIGHT_LESS_THAN_1);

        if (door.x < 2)
            reasons.add(DOOR_OUT_OF_BOUNDS_LEFT);

        if (door.x + door.width > getWallWidth(houseDimensions, door.side) - 2)
            reasons.add(DOOR_OUT_OF_BOUNDS_RIGHT);

        if (door.height > houseDimensions.height)
            reasons.add(DOOR_OUT_OF_BOUNDS_TOP);
    }

    /**
     * Validates the {@link Window} of the provided {@link HouseSpecification}.
     *
     * @param specification The The {@link HouseSpecification} to validate the {@link Window} of.
     * @param reasons       The list of {@link Reason}s why the validation failed. This list is filled with {@link Reason}s
     *                      why the {@link Door} is not valid.
     */
    private void validateWindow(HouseSpecification specification, List<Reason> reasons)
    {
        Cube   houseDimensions = specification.dimensions;
        Window window          = specification.window;

        if (window.y < 1)
            reasons.add(WINDOW_POSITION_Y_LESS_THAN_1);

        if (window.width < 1)
            reasons.add(WINDOW_WIDTH_LESS_THAN_1);

        if (window.height < 1)
            reasons.add(WINDOW_HEIGHT_LESS_THAN_1);

        if (window.x < 2)
            reasons.add(WINDOW_OUT_OF_BOUNDS_LEFT);

        if (window.x + window.width > getWallWidth(houseDimensions, window.side) - 2)
            reasons.add(WINDOW_OUT_OF_BOUNDS_RIGHT);

        if (window.height + window.y > specification.dimensions.height)
            reasons.add(WINDOW_OUT_OF_BOUNDS_TOP);
    }

    /**
     * Returns the width of the provided {@link Side} of the provided house dimensions.
     *
     * @param house The dimensions of the house.
     * @param side  The {@link Side} of the house, to return the width of.
     * @return The width of the provided {@link Side} of the provided house dimensions.
     */
    private int getWallWidth(Cube house, Side side)
    {
        if (side == Side.FRONT || side == Side.BACK)
            return house.width;

        if (side == Side.LEFT || side == Side.RIGHT)
            return house.depth;

        throw new UnsupportedOperationException();
    }

    /**
     * Builds a {@link Wall} of the provided {@link HouseSpecification}.
     *
     * @param specifications The {@link HouseSpecification} to build a {@link Wall} from.
     * @param side           The {@link Side} of the {@link HouseSpecification} {@link Wall} to build.
     * @return The resulting {@link Wall}.
     */
    private Wall buildWall(HouseSpecification specifications, Side side)
    {
        WallBuilder builder = new WallBuilder();
        this.currentOffset = 0;

        while (builder.getCurrentPointer().y < specifications.dimensions.height) {

            // Ensure that the bricklayer starts at an offset of 2 bricks on odd rows.
            this.currentOffset = 0;
            int width = getWallWidth(specifications.dimensions, side) - BRICK_WIDTH;
            if (builder.getCurrentPointer().y % 2 != 0) {
                builder.move(2);
                width += BRICK_WIDTH;
            }

            while (builder.getCurrentPointer().x < width) {

                Position position = builder.getCurrentPointer();

                // When the door is in the way, skip the distance of the door, and add the difference to the current offset.
                if (specifications.door != null && !fits(1, position, side, specifications.door.side, specifications.door)) {
                    builder.move(specifications.door.width);
                    currentOffset = currentOffset + specifications.door.width % BRICK_LARGE;
                    continue;
                }

                // When the window is in the way, skip the distance of the window, and add the difference to the current offset.
                if (specifications.window != null && !fits(1, position, side, specifications.window.side, specifications.window)) {
                    builder.move(specifications.window.width);
                    currentOffset = currentOffset + specifications.window.width % BRICK_LARGE;
                    continue;
                }

                // When the current offset is not 0, place a brick of size currentOffset.
                if (currentOffset != 0 && place(builder, currentOffset, width, side, specifications)) {
                    currentOffset = 0;
                    continue;
                }

                // If possible, place a large brick.
                if (place(builder, BRICK_LARGE, width, side, specifications))
                    continue;

                // If possible, place a medium brick.
                if (place(builder, BRICK_MEDIUM, width, side, specifications)) {
                    currentOffset += BRICK_MEDIUM;
                    continue;
                }

                // If possible, place a small brick.
                if (place(builder, BRICK_SMALL, width, side, specifications)) {
                    currentOffset += BRICK_SMALL;
                    continue;
                }

                builder.move(1);
            }

            builder.up();
        }

        return builder.build();
    }

    /**
     * Places the provided {@code brick} on the provided {@link WallBuilder}.
     *
     * @param builder        The {@link WallBuilder} to place the provided {@code brick} on.
     * @param brick          The size of the brick to place.
     * @param width          The width of the wall being built.
     * @param side           The {@link Side} of the {@link HouseSpecification} the brick is being placed on.
     * @param specifications The {@link HouseSpecification} being built from.
     * @return {@code true} if the provided {@code brick} could be placed.
     */
    private boolean place(WallBuilder builder, int brick, int width, Side side, HouseSpecification specifications)
    {
        Position position = builder.getCurrentPointer();
        if (position.x + brick <= width) {

            boolean fitsWindow = specifications.window == null || fits(brick, position, side, specifications.window.side, specifications.window);
            boolean fitsDoor   = specifications.door == null || fits(brick, position, side, specifications.door.side, specifications.door);

            if (fitsWindow && fitsDoor) {
                builder.place(brick);
                return true;
            }
        }

        return false;
    }

    /**
     * Checks if a brick of the provided size, placed at the provided position is blocked by the provided {@link PositionedSquare}.
     *
     * @param brick            The size of the brick.
     * @param position         The position where the brick is to be placed.
     * @param wallSide         The wall currently being built.
     * @param areaSide         The size where the {@link PositionedSquare} is.
     * @param positionedSquare The area being blocked.
     * @return {@code true} if a brick of the provided size is not blocked.
     */
    private boolean fits(int brick, Position position, Side wallSide, Side areaSide, PositionedSquare positionedSquare)
    {
        for (int l = 0; l < brick; l++) {
            Position p = new Position(position.x + l, position.y);
            if (!(positionedSquare == null || wallSide != areaSide || !positionedSquare.contains(p))) {
                return false;
            }
        }

        return true;
    }
}
