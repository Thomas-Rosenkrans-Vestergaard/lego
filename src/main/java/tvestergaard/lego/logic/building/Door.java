package tvestergaard.lego.logic.building;

public class Door extends AbstractArea
{

    /**
     * The position of the {@link Door} on the provided {@link Side}.
     */
    public final Position position;

    /**
     * The side of the {@link House} to place the {@link Door} on.
     */
    public final Side side;

    /**
     * Creates a new {@link Door}.
     *
     * @param width    The width of the {@link Door}.
     * @param height   The height of the {@link Door}.
     * @param position The {@link Position} of the {@link Door} on the chosen {@link Side}.
     * @param side     The {@link Side} of the {@link House} where the {@link Door} is located.
     * @throws InvalidDoorException When the arguments provided created an invalid {@link Door}.
     */
    public Door(int width, int height, Position position, Side side) throws InvalidDoorException
    {
        super(width, height);

        if (height < 1)
            throw new InvalidDoorException("The height of the door must exceed 0.", this);
        if (width < 1)
            throw new InvalidDoorException("The width of the door must exceed 0.", this);
        if (position.y < 1)
            throw new InvalidDoorException("Door position.y must not be <= 0", this);

        this.position = position;
        this.side = side;
    }
}
