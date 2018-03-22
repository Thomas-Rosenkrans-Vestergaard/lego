package tvestergaard.lego.logic.building;

/**
 * Represents some {@link Door} on a {@link House}.
 */
public class Door extends PositionedSquare
{

    /**
     * The side of the {@link HouseSpecification} to place the {@link Door} on.
     */
    public final Side side;

    /**
     * Creates a new {@link Door}.
     *
     * @param dimensions The dimensions of the {@link Door} represented by an {@link Square} in two dimensions.
     * @param position   The {@link Position} of the {@link Door} on the chosen {@link Side}.
     * @param side       The {@link Side} of the {@link HouseSpecification} where the {@link Door} is located.
     * @throws InvalidDoorException When the arguments provided created an invalid {@link Door}.
     */
    public Door(Square dimensions, Position position, Side side) throws InvalidDoorException
    {
        super(position, dimensions);

        if (dimensions.height < 1)
            throw new InvalidDoorException("The height of the door must exceed 0.", this);

        if (dimensions.width < 1)
            throw new InvalidDoorException("The width of the door must exceed 0.", this);

        if (position.y != 0)
            throw new InvalidDoorException("Door position.y must = 0", this);

        if (position.x < 2)
            throw new InvalidDoorException("Door position.x must not be < 2.", this);

        this.side = side;
    }

    /**
     * Returns the side of the {@link HouseSpecification} to place the {@link Door} on.
     *
     * @return The side of the {@link HouseSpecification} to place the {@link Door} on.
     */
    public Side getSide()
    {
        return this.side;
    }
}
