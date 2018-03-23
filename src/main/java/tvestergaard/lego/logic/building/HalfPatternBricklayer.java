package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Cube;
import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.logic.geometry.PositionedSquare;

public class HalfPatternBricklayer implements Bricklayer
{

    private static final int BRICK_LARGE  = 4;
    private static final int BRICK_MEDIUM = 2;
    private static final int BRICK_SMALL  = 1;
    private static final int BRICK_WIDTH  = 2;

    private int currentOffset = 0;

    public HalfPatternBricklayer()
    {
        currentOffset = 0;
    }

    /**
     * Creates the {@link House} specified from the provided {@link HouseSpecification}.
     *
     * @param specifications The specifications for the {@link House} to build.
     * @return The {@link House} created from the provided {@link HouseSpecification}.
     * @throws IllegalHouseDimensionsException When the dimensions of the provided {@link HouseSpecification} is invalid.
     * @throws IllegalWindowException          When the placement of the {@link Window} specified in the provided
     *                                         {@link HouseSpecification} is invalid.
     * @throws IllegalDoorException            When the placement of the {@link Door} specified in the provided
     *                                         {@link HouseSpecification} is invalid.
     * @throws IllegalCollisionException       When the placement of the {@link Door} or {@link Window} specified in the provided
     *                                         {@link HouseSpecification} collide.
     */
    @Override
    public House lay(HouseSpecification specifications) throws IllegalHouseDimensionsException,
                                                               IllegalWindowException,
                                                               IllegalDoorException,
                                                               IllegalCollisionException
    {
        Wall front = buildWall(specifications, Side.FRONT);
        Wall back  = buildWall(specifications, Side.BACK);
        Wall right = buildWall(specifications, Side.RIGHT);
        Wall left  = buildWall(specifications, Side.LEFT);

        return new House(specifications.dimensions, specifications.door, specifications.window, front, back, right, left);
    }

    public void validateSpecifications(HouseSpecification specification) throws
                                                                         IllegalHouseDimensionsException,
                                                                         IllegalWindowException,
                                                                         IllegalDoorException,
                                                                         IllegalCollisionException
    {

        Cube dimensions = specification.getDimensions();
        if (dimensions.width < 4)
            throw new IllegalHouseDimensionsException(IllegalHouseDimensionsException.Reason.WIDTH_LESS_THAN_4, specification);
        if (dimensions.depth < 4)
            throw new IllegalHouseDimensionsException(IllegalHouseDimensionsException.Reason.DEPTH_LESS_THAN_4, specification);
        if (dimensions.height < 1)
            throw new IllegalHouseDimensionsException(IllegalHouseDimensionsException.Reason.HEIGHT_LESS_THAN_1, specification);

        if (specification.door != null)
            validateDoor(specification);

        if (specification.window != null)
            validateWindow(specification);

        if (specification.window != null && specification.dimensions != null)
            if (specification.window.overlaps(specification.door))
                throw new IllegalCollisionException(specification);
    }

    private void validateWindow(HouseSpecification specification) throws IllegalWindowException
    {
        Cube   houseDimensions = specification.dimensions;
        Window window          = specification.window;

        if (window.y < 1)
            throw new IllegalWindowException(IllegalWindowException.Reason.POSITION_Y_LESS_THAN_1, specification);

        if (window.x < 2)
            throw new IllegalWindowException(IllegalWindowException.Reason.PLACEMENT_OUT_OF_BOUNDS_LEFT, specification);

        int windowWallWidth = getWallWidth(houseDimensions, window.side);
        if (window.x + window.width <= windowWallWidth - 2)
            throw new IllegalWindowException(IllegalWindowException.Reason.PLACEMENT_OUT_OF_BOUNDS_RIGHT, specification);

        if (window.height + window.y <= specification.dimensions.height)
            throw new IllegalWindowException(IllegalWindowException.Reason.PLACEMENT_OUT_OF_BOUNDS_TOP, specification);

        if (window.width < 1)
            throw new IllegalWindowException(IllegalWindowException.Reason.DIMENSIONS_WIDTH_LESS_THAN_1, specification);

        if (window.height < 1)
            throw new IllegalWindowException(IllegalWindowException.Reason.DIMENSIONS_HEIGHT_LESS_THAN_1, specification);
    }

    private void validateDoor(HouseSpecification specification) throws IllegalDoorException
    {
        Cube houseDimensions = specification.dimensions;
        Door door            = specification.door;

        if (door.y != 0)
            throw new IllegalDoorException(IllegalDoorException.Reason.POSITION_Y_NOT_ZERO, specification);

        if (door.x < 2)
            throw new IllegalDoorException(IllegalDoorException.Reason.PLACEMENT_OUT_OF_BOUNDS_LEFT, specification);

        int doorWallWidth = getWallWidth(houseDimensions, door.side);
        if (door.x + door.width <= doorWallWidth - 2)
            throw new IllegalDoorException(IllegalDoorException.Reason.PLACEMENT_OUT_OF_BOUNDS_RIGHT, specification);

        if (door.height <= houseDimensions.height)
            throw new IllegalDoorException(IllegalDoorException.Reason.PLACEMENT_OUT_OF_BOUNDS_TOP, specification);

        if (door.width < 1)
            throw new IllegalDoorException(IllegalDoorException.Reason.DIMENSIONS_WIDTH_LESS_THAN_1, specification);

        if (door.height < 1)
            throw new IllegalDoorException(IllegalDoorException.Reason.DIMENSIONS_HEIGHT_LESS_THAN_1, specification);
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
