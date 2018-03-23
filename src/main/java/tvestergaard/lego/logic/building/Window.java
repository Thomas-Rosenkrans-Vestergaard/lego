package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Position;
import tvestergaard.lego.logic.geometry.PositionedSquare;

public class Window extends PositionedSquare
{

    /**
     * The side of the {@link HouseSpecification} to place the {@link Window} on.
     */
    public final Side side;

    /**
     * Creates a new {@link Window}.
     *
     * @param width    The width of the {@link Window}.
     * @param height   The height of the {@link Window}.
     * @param position The {@link Position} of the {@link Window} on the chosen {@link Side}.
     * @param side     The {@link Side} of the {@link HouseSpecification} where the {@link Window} is located.
     */
    public Window(int width, int height, Position position, Side side)
    {

        super(width, height, position);
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
