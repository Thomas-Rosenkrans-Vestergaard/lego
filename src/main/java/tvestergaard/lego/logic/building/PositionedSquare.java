package tvestergaard.lego.logic.building;

public class PositionedSquare
{

    public final Position position;
    public final Square   square;

    public PositionedSquare(Position position, Square square)
    {
        this.position = position;
        this.square = square;
    }

    public boolean contains(Position position)
    {
        return position.x >= this.position.x &&
               position.y >= this.position.y &&
               position.x < this.position.x + square.width &&
               position.y < this.position.y + square.height;
    }

    public Position getPosition()
    {
        return this.position;
    }

    public Square getSquare()
    {
        return this.square;
    }
}
