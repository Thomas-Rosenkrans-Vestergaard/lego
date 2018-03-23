package tvestergaard.lego.logic.building;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a collection of {@link Brick}s in two dimensions.
 */
public class Wall
{

    /**
     * The {@link Brick}s making up the {@link Wall}.
     */
    private final List<Brick> bricks;

    /**
     * The number of 4x2 pieces used in the {@link Wall}.
     */
    public final int fourPieces;

    /**
     * The number of 2x2 pieces used in the {@link Wall}.
     */
    public final int twoPieces;

    /**
     * The number of 1x2 pieces used in the {@link Wall}.
     */
    public final int onePieces;

    /**
     * Creates a new {@link Wall}.
     *
     * @param bricks     The {@link Brick}s making up the {@link Wall}.
     * @param fourPieces The number of 4x2 pieces used in the {@link Wall}.
     * @param twoPieces  The number of 2x2 pieces used in the {@link Wall}.
     * @param onePieces  The number of 1x2 pieces used in the {@link Wall}.
     */
    public Wall(List<Brick> bricks, int fourPieces, int twoPieces, int onePieces)
    {
        this.bricks = bricks;
        this.fourPieces = fourPieces;
        this.twoPieces = twoPieces;
        this.onePieces = onePieces;
    }

    /**
     * Returns the {@link Brick}s making up the {@link Wall}.
     *
     * @return The {@link Brick}s making up the {@link Wall}.
     */
    public List<Brick> getBricks()
    {
        return new ArrayList<>(this.bricks);
    }

    /**
     * Returns the number of 4x2 pieces used in the {@link Wall}.
     *
     * @return The number of 4x2 pieces used in the {@link Wall}.
     */
    public int getFourPieces()
    {
        return this.fourPieces;
    }

    /**
     * Returns the number of 2x2 pieces used in the {@link Wall}.
     *
     * @return The number of 2x2 pieces used in the {@link Wall}.
     */
    public int getTwoPieces()
    {
        return this.twoPieces;
    }

    /**
     * Returns the number of 1x2 pieces used in the {@link Wall}.
     *
     * @return The number of 1x2 pieces used in the {@link Wall}.
     */
    public int getOnePieces()
    {
        return this.onePieces;
    }
}
