package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Cube;

/**
 * A {@link House} built from {@link Brick}s with four {@link Wall} and an optional {@link Door} or {@link Window}.
 */
public class House
{

    /**
     * The dimensions of the {@link House} represented by a {@link Cube}.
     */
    private final Cube dimensions;

    /**
     * The {@link Door} placed on the {@link House}. When {@code null} the {@link House} does not have a {@link Door}.
     */
    private final Door door;

    /**
     * The {@link Window} placed on the {@link House}. When {@code null} the {@link House} does not have a {@link Window}.
     */
    private final Window window;

    /**
     * The {@link Side#FRONT} {@link Wall} of the {@link House}.
     */
    private final Wall front;

    /**
     * The {@link Side#BACK} {@link Wall} of the {@link House}.
     */
    private final Wall back;

    /**
     * The {@link Side#RIGHT} {@link Wall} of the {@link House}.
     */
    private final Wall right;

    /**
     * The {@link Side#LEFT} {@link Wall} of the {@link House}.
     */
    private final Wall left;

    /**
     * Creates a new {@link House} using the provided {@link House}.
     *
     * @param dimensions The dimensions of the {@link House}.
     * @param door       The {@link Door} placed on the {@link House}.  When {@code null} the {@link House} does not
     *                   have a {@link Door}.
     * @param window     The {@link Window} placed in the {@link House}.  When {@code null} the {@link House} does not
     *                   have a {@link Window}.
     * @param front      The {@link Side#FRONT} {@link Wall} of the {@link House}.
     * @param back       The {@link Side#BACK} {@link Wall} of the {@link House}.
     * @param right      The {@link Side#RIGHT} {@link Wall} of the {@link House}.
     * @param left       The {@link Side#LEFT} {@link Wall} of the {@link House}.
     */
    public House(Cube dimensions, Door door, Window window, Wall front, Wall back, Wall right, Wall left)
    {
        this.dimensions = dimensions;
        this.door = door;
        this.window = window;
        this.front = front;
        this.back = back;
        this.right = right;
        this.left = left;
    }

    /**
     * Returns The dimensions of the {@link House} represented by a {@link Cube}.
     *
     * @return The dimensions of the {@link House} represented by a {@link Cube}.
     */
    public Cube getDimensions()
    {
        return this.dimensions;
    }

    /**
     * Returns the {@link Door} placed on the {@link House}. When {@code null} the {@link House} does not have a
     * {@link Door}.
     *
     * @return The {@link Door} placed on the {@link House}. When {@code null} the {@link House} does not have a
     * {@link Door}.
     */
    public Door getDoor()
    {
        return this.door;
    }

    /**
     * Returns the {@link Window} placed on the {@link House}. When {@code null} the {@link House} does not have a
     * {@link Window}.
     *
     * @return The {@link Window} placed on the {@link House}. When {@code null} the {@link House} does not have a
     * {@link Window}.
     */
    public Window getWindow()
    {
        return this.window;
    }

    /**
     * Returns the {@link Side#FRONT} {@link Wall} of the {@link House}.
     *
     * @return The {@link Side#FRONT} {@link Wall} of the {@link House}.
     */
    public Wall getFront()
    {
        return this.front;
    }

    /**
     * Returns the {@link Side#BACK} {@link Wall} of the {@link House}.
     *
     * @return The {@link Side#BACK} {@link Wall} of the {@link House}.
     */
    public Wall getBack()
    {
        return this.back;
    }

    /**
     * Returns the {@link Side#RIGHT} {@link Wall} of the {@link House}.
     *
     * @return The {@link Side#RIGHT} {@link Wall} of the {@link House}.
     */
    public Wall getRight()
    {
        return this.right;
    }

    /**
     * Returns the {@link Side#LEFT} {@link Wall} of the {@link House}.
     *
     * @return The {@link Side#LEFT} {@link Wall} of the {@link House}.
     */
    public Wall getLeft()
    {
        return this.left;
    }

    public int getFourPieces()
    {
        return front.getFourPieces() +
               back.getFourPieces() +
               left.getFourPieces() +
               right.getFourPieces();
    }


    public int getTwoPieces()
    {
        return front.getTwoPieces() +
               back.getTwoPieces() +
               left.getTwoPieces() +
               right.getTwoPieces();
    }


    public int getOnePieces()
    {
        return front.getOnePieces() +
               back.getOnePieces() +
               left.getOnePieces() +
               right.getOnePieces();
    }
}
