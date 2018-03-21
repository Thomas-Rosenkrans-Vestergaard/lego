package tvestergaard.lego.logic.building;

public class Door extends AbstractArea
{

    private final Position position;

    public Door(int height, int width, Position position)
    {
        super(height, width);
        this.position = position;
    }

    public Door(int height, int width, int x, int y)
    {
        this(height, width, new Position(x, y));
    }
}
