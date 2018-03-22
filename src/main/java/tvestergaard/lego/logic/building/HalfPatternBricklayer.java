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
        Window              window              = new Window(Square.of(4, 2), Position.of(6, 1), Side.FRONT);
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
        WallBuilder builder    = new WallBuilder();
        int         currentRow = 0;

        while (currentRow < specifications.dimensions.height) {

            int width        = specifications.dimensions.width - BRICK_WIDTH;
            int currentIndex = 0;
            if (currentRow % 2 != 0) {
                builder.move(2);
                width += BRICK_WIDTH;
                currentIndex += 2;
            }

            while (currentIndex < width) {

                if (currentIndex + BRICK_LARGE <= width && notBlocked(currentIndex, currentRow, BRICK_LARGE, side, specifications)) {
                    builder.place(BRICK_LARGE);
                    currentIndex += BRICK_LARGE;
                    continue;
                }

                if (currentIndex + BRICK_MEDIUM <= width && notBlocked(currentIndex, currentRow, BRICK_MEDIUM, side, specifications)) {
                    builder.place(BRICK_MEDIUM);
                    currentIndex += BRICK_MEDIUM;
                    continue;
                }

                if (currentIndex + BRICK_SMALL <= width && notBlocked(currentIndex, currentRow, BRICK_SMALL, side, specifications)) {
                    builder.place(BRICK_SMALL);
                    currentIndex += BRICK_SMALL;
                    continue;
                }
            }

            currentRow++;
            builder.up();
        }

        return builder.build();
    }

    private boolean notBlocked(int currentIndex, int row, int brick, Side side, HouseSpecifications specifications)
    {
        if (specifications.door == null && specifications.window == null)
            return false;

        for (int l = 0; l < brick; l++) {
            Position position = Position.of(currentIndex + l, row);

            boolean check = (specifications.window == null || specifications.window.side != side || !specifications.window.contains(position)) &&
                            (specifications.door == null || specifications.door.side != side || !specifications.door.contains(position));
            if (!check)
                return false;
        }

        return true;
    }

    private static void render(House house, HouseSpecifications houseSpecifications)
    {
        System.out.println(houseSpecifications.dimensions.width);
        System.out.println(houseSpecifications.dimensions.height);
        System.out.println(house.getFront().getBricks().size());

        System.out.println(house.getFront().getBricks());
        System.out.println(house.getBack().getBricks());
        System.out.println(house.getLeft().getBricks());
        System.out.println(house.getRight().getBricks());

        char[][] chars = new char[houseSpecifications.dimensions.width][houseSpecifications.dimensions.height];

        for (int x = 0; x < houseSpecifications.dimensions.width; x++) {
            for (int y = 0; y < houseSpecifications.dimensions.height; y++) {
                chars[x][y] = '_';
            }
        }

        System.out.println("Front");
        for (Brick brick : house.getFront().getBricks()) {
            int x = brick.position.getX();
            int y = brick.position.getY();
            System.out.println("x=" + x);
            System.out.println("y=" + y);
            for (int l = 0; l < brick.length; l++) {
                chars[x + l][y] = 'X';
            }
        }

        for (int y = houseSpecifications.dimensions.height - 1; y >= 0; y--) {
            for (int x = 0; x < houseSpecifications.dimensions.width; x++) {
                char character = chars[x][y];
                System.out.print(character);
            }

            System.out.println();
        }
    }
}
