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

    private int currentOffset = 0;

    public HalfOffsetBricklayer()
    {
        currentOffset = 0;
    }

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

    public void validateSpecifications(HouseSpecification specification) throws BricklayerException
    {

        List<Reason> reasons = new ArrayList<>();

        Cube dimensions = specification.getDimensions();
        if (dimensions.width < 4)
            reasons.add(WIDTH_LESS_THAN_4);
        if (dimensions.height < 1)
            reasons.add(HEIGHT_LESS_THAN_1);
        if (dimensions.depth < 4)
            reasons.add(DEPTH_LESS_THAN_4);

        if (specification.door != null)
            validateDoor(specification, reasons);

        if (specification.window != null)
            validateWindow(specification, reasons);

        if (specification.window != null && specification.dimensions != null)
            if (specification.window.overlaps(specification.door))
                reasons.add(DOOR_WINDOW_COLLISION);

        if (!reasons.isEmpty()) {
            throw new BricklayerException(specification, reasons);
        }
    }

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

    private int getWallWidth(Cube house, Side side)
    {
        if (side == Side.FRONT || side == Side.BACK)
            return house.width;

        if (side == Side.LEFT || side == Side.RIGHT)
            return house.depth;

        throw new UnsupportedOperationException();
    }

    private Wall buildWall(HouseSpecification specifications, Side side)
    {
        WallBuilder builder = new WallBuilder();
        this.currentOffset = 0;

        while (builder.getCurrentPointer().y < specifications.dimensions.height) {

            this.currentOffset = 0;
            int width = specifications.dimensions.width - BRICK_WIDTH;
            if (builder.getCurrentPointer().y % 2 != 0) {
                builder.move(2);
                width += BRICK_WIDTH;
            }

            while (builder.getCurrentPointer().x < width) {

                Position position = builder.getCurrentPointer();

                if (specifications.door != null && !fits(1, position, side, specifications.door.side, specifications.door)) {
                    builder.move(specifications.door.width);
                    currentOffset = currentOffset + specifications.door.width % BRICK_LARGE;
                    continue;
                }

                if (specifications.window != null && !fits(1, position, side, specifications.window.side, specifications.window)) {
                    builder.move(specifications.window.width);
                    currentOffset = currentOffset + specifications.window.width % BRICK_LARGE;
                    continue;
                }

                if (currentOffset != 0) {
                    if (place(builder, currentOffset, width, side, specifications)) {
                        currentOffset = 0;
                        continue;
                    }
                }

                if (place(builder, BRICK_LARGE, width, side, specifications))
                    continue;
                if (place(builder, BRICK_MEDIUM, width, side, specifications)) {
                    currentOffset += BRICK_MEDIUM;
                    continue;
                }
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
