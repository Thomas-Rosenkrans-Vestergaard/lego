package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Cube;

/**
 * A {@link House} built from {@link Brick}s with four {@link Wall} and an optional {@link Door} or {@link Window}.
 */
public class House extends Cube
{

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
     * @param width  The width of the {@link House}.
     * @param height The height of the {@link House}.
     * @param depth  The depth of the {@link House}.
     * @param door   The {@link Door} placed on the {@link House}.  When {@code null} the {@link House} does not
     *               have a {@link Door}.
     * @param window The {@link Window} placed in the {@link House}.  When {@code null} the {@link House} does not
     *               have a {@link Window}.
     * @param front  The {@link Side#FRONT} {@link Wall} of the {@link House}.
     * @param back   The {@link Side#BACK} {@link Wall} of the {@link House}.
     * @param right  The {@link Side#RIGHT} {@link Wall} of the {@link House}.
     * @param left   The {@link Side#LEFT} {@link Wall} of the {@link House}.
     */
    public House(int width, int height, int depth, Door door, Window window, Wall front, Wall back, Wall right, Wall left)
    {
        super(width, height, depth);
        this.door = door;
        this.window = window;
        this.front = front;
        this.back = back;
        this.right = right;
        this.left = left;
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

    /**
     * Returns the number of 4x2 pieces used to create the {@link House}.
     *
     * @return The number of 4x2 pieces used to create the {@link House}.
     */
    public int getFourPieces()
    {
        return front.getFourPieces() +
                back.getFourPieces() +
                left.getFourPieces() +
                right.getFourPieces();
    }

    /**
     * Returns the number of 2x2 pieces used to create the {@link House}.
     *
     * @return The number of 2x2 pieces used to create the {@link House}.
     */
    public int getTwoPieces()
    {
        return front.getTwoPieces() +
                back.getTwoPieces() +
                left.getTwoPieces() +
                right.getTwoPieces();
    }

    /**
     * Returns the number of 1x2 pieces used to create the {@link House}.
     *
     * @return The number of 1x2 pieces used to create the {@link House}.
     */
    public int getOnePieces()
    {
        return front.getOnePieces() +
                back.getOnePieces() +
                left.getOnePieces() +
                right.getOnePieces();
    }
}
