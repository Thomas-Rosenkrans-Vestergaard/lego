package tvestergaard.lego.logic.building;

public class PositionedSquare
{

    public final Position position;
    public final Square   dimensions;

    public PositionedSquare(Position position, Square dimensions)
    {
        this.position = position;
        this.dimensions = dimensions;
    }

    public boolean contains(Position position)
    {
        return position.x >= this.position.x &&
               position.y >= this.position.y &&
               position.x < this.position.x + dimensions.width &&
               position.y < this.position.y + dimensions.height;
    }

    public Position getPosition()
    {
        return this.position;
    }

    public Square getDimensions()
    {
        return this.dimensions;
    }

    public boolean overlaps(PositionedSquare other)
    {
        return this.position.x < other.position.x + other.dimensions.width &&
               this.position.x + this.dimensions.width > other.position.x &&
               this.position.y < other.position.y + other.dimensions.height &&
               this.position.y + other.dimensions.height > other.position.y;
    }
}
