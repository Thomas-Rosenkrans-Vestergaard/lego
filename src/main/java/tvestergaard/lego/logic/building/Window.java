package tvestergaard.lego.logic.building;

public class Window extends PositionedSquare
{

    /**
     * The side of the {@link HouseSpecification} to place the {@link Window} on.
     */
    public final Side side;

    /**
     * Creates a new {@link Window}.
     *
     * @param dimensions The dimensions of the {@link Window} represented by an {@link Square} in two dimensions.
     * @param position   The {@link Position} of the {@link Window} on the chosen {@link Side}.
     * @param side       The {@link Side} of the {@link HouseSpecification} where the {@link Window} is located.
     * @throws InvalidWindowException When the arguments provided created an invalid {@link Window}.
     */
    public Window(Square dimensions, Position position, Side side) throws InvalidWindowException
    {

        super(position, dimensions);

        if (dimensions.height < 1)
            throw new InvalidWindowException("The height of the Window must exceed 0.", this);

        if (dimensions.width < 1)
            throw new InvalidWindowException("The width of the Window must exceed 0.", this);

        if (position.y != 0)
            throw new InvalidWindowException("Window position.y must != 0", this);

        if (position.x < 2)
            throw new InvalidWindowException("Window position.x must not be < 2.", this);

        this.side = side;
    }

    /**
     * Returns the side of the {@link HouseSpecification} to place the {@link Window} on.
     *
     * @return The side of the {@link HouseSpecification} to place the {@link Window} on.
     */
    public Side getSide()
    {
        return this.side;
    }
}
