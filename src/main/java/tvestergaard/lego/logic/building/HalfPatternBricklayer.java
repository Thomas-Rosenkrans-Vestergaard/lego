package tvestergaard.lego.logic.building;

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

    @Override public House lay(HouseSpecification specifications)
    {
        Wall front = buildWall(specifications, Side.FRONT);
        Wall back  = buildWall(specifications, Side.BACK);
        Wall right = buildWall(specifications, Side.RIGHT);
        Wall left  = buildWall(specifications, Side.LEFT);

        return new House(specifications.dimensions, specifications.door, specifications.window, front, back, right, left);
    }

    private Wall buildWall(HouseSpecification specifications, Side side)
    {
        WallBuilder builder = new WallBuilder();
        this.currentOffset = 0;

        while (builder.getCurrentPosition().y < specifications.dimensions.height) {

            this.currentOffset = 0;
            int width = specifications.dimensions.width - BRICK_WIDTH;
            if (builder.getCurrentPosition().y % 2 != 0) {
                builder.move(2);
                width += BRICK_WIDTH;
            }

            while (builder.getCurrentPosition().x < width) {

                Position position = builder.getCurrentPosition();

                if (specifications.door != null && !fits(1, position, side, specifications.door.side, specifications.door)) {
                    builder.move(specifications.door.square.width);
                    currentOffset = currentOffset + specifications.door.square.width % BRICK_LARGE;
                    continue;
                }

                if (specifications.window != null && !fits(1, position, side, specifications.window.side, specifications.window)) {
                    builder.move(specifications.window.square.width);
                    currentOffset = currentOffset + specifications.window.square.width % BRICK_LARGE;
                    continue;
                }

                if (currentOffset != 0 && place(builder, currentOffset, width, side, specifications)) {
                    currentOffset = 0;
                    continue;
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
        Position position = builder.getCurrentPosition();
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
