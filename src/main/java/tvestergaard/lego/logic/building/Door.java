package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.logic.geometry.PositionedSquare;

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
     * @param width    The width of the door {@link Door}.
     * @param height   The height of the {@link Door}.
     * @param position The {@link Position} of the {@link Door} on the chosen {@link Side}.
     * @param side     The {@link Side} of the {@link HouseSpecification} where the {@link Door} is located.
     */
    public Door(int width, int height, Position position, Side side)
    {
        super(width, height, position);
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
