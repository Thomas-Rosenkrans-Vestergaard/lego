package tvestergaard.lego.logic.building;

public class HalfPatternBricklayer implements Bricklayer
{

    private static final int BRICK_LARGE  = 4;
    private static final int BRICK_MEDIUM = 2;
    private static final int BRICK_SMALL  = 1;
    private static final int BRICK_WIDTH  = 2;

    public static void main(String[] args) throws Exception
    {
        Bricklayer          bricklayer          = new HalfPatternBricklayer();
        Door                door                = new Door(Square.of(4, 3), Position.of(2, 0), Side.FRONT);
        Window              window              = new Window(Square.of(4, 2), Position.of(8, 1), Side.FRONT);
        HouseSpecifications houseSpecifications = new HouseSpecifications(new Cube(20, 5, 15), door, window);
        House               house               = bricklayer.lay(houseSpecifications);
        render(house, houseSpecifications);
    }

    @Override public House lay(HouseSpecifications specifications)
    {
        Wall front = buildWall(specifications, Side.FRONT);
        Wall back  = buildWall(specifications, Side.BACK);
        Wall right = buildWall(specifications, Side.RIGHT);
        Wall left  = buildWall(specifications, Side.LEFT);

        return new House(front, back, right, left);
    }

    private Wall buildWall(HouseSpecifications specifications, Side side)
    {
        WallBuilder builder = new WallBuilder();
        while (builder.getCurrentPosition().y < specifications.dimensions.height) {

            int width = specifications.dimensions.width - BRICK_WIDTH;
            if (builder.getCurrentPosition().y % 2 != 0) {
                builder.move(2);
                width += BRICK_WIDTH;
            }

            while (builder.getCurrentPosition().x < width) {
                Position position = builder.getCurrentPosition();

                if (!fits(1, position, side, specifications.door.side, specifications.door)) {
                    builder.move(specifications.door.square.width);
                    continue;
                }

                if (!fits(1, position, side, specifications.window.side, specifications.window)) {
                    builder.move(specifications.window.square.width);
                    continue;
                }

                if (place(builder, BRICK_LARGE, width, side, specifications))
                    continue;
                if (place(builder, BRICK_MEDIUM, width, side, specifications))
                    continue;
                if (place(builder, BRICK_SMALL, width, side, specifications))
                    continue;

                builder.move(1);
            }

            builder.up();
        }

        return builder.build();
    }

    private boolean place(WallBuilder builder, int brick, int width, Side side, HouseSpecifications specifications)
    {
        Position position = builder.getCurrentPosition();
        if (position.x + brick <= width) {

            boolean fitsWindow = fits(brick, position, side, specifications.window.side, specifications.window);
            boolean fitsDoor   = fits(brick, position, side, specifications.door.side, specifications.door);

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

    private static void render(House house, HouseSpecifications houseSpecifications)
    {
        System.out.println("Front");
        System.out.println(house.getFront().getBricks());
        render(house.getFront(), houseSpecifications);

        System.out.println("Back");
        System.out.println(house.getBack().getBricks());
        render(house.getBack(), houseSpecifications);

        System.out.println("Left");
        System.out.println(house.getLeft().getBricks());
        render(house.getLeft(), houseSpecifications);

        System.out.println("Right");
        System.out.println(house.getRight().getBricks());
        render(house.getRight(), houseSpecifications);
    }

    private static void render(Wall wall, HouseSpecifications specifications)
    {


        char[][] chars = new char[specifications.dimensions.width][specifications.dimensions.height];

        for (int x = 0; x < specifications.dimensions.width; x++) {
            for (int y = 0; y < specifications.dimensions.height; y++) {
                chars[x][y] = '_';
            }
        }

        for (Brick brick : wall.getBricks()) {
            int x = brick.position.getX();
            int y = brick.position.getY();
            for (int l = 0; l < brick.length; l++) {
                chars[x + l][y] = 'X';
            }
        }

        for (int y = specifications.dimensions.height - 1; y >= 0; y--) {
            for (int x = 0; x < specifications.dimensions.width; x++) {
                char character = chars[x][y];
                System.out.print(character);
            }

            System.out.println();
        }

    }
}
