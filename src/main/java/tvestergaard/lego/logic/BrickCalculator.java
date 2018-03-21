package tvestergaard.lego.logic;

public class BrickCalculator
{

    private final int BRICK_WIDTH  = 2;
    private final int LARGE_BRICK  = 4;
    private final int MEDIUM_BRICK = 2;
    private final int SMALL_BRICK  = 1;

    private final int NONE  = 0;
    private final int FRONT = 1;
    private final int LEFT  = 2;
    private final int RIGHT = 3;
    private final int BACK  = 4;

    public House calculate(int width, int height, int depth, int door, int window) throws IllegalHouseDimensionsException
    {
        if (width < 8 || height < 4 || depth < 8)
            throw new IllegalHouseDimensionsException();

        if (door == NONE && window == NONE && width % 6 == 0 && depth % 6 == 0) {
            
        }
    }
}
