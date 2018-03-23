package tvestergaard.lego.logic.building;

import tvestergaard.lego.logic.geometry.Position;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for creating instances of {@link Wall}. The class maintains a {@link Position} acting as
 * the {@code current pointer}. When calling the {@link WallBuilder#place(int)} method, a brick of the provided size
 * is inserted at the {@code current pointer}. Methods also exists that can move the {@code current pointer} to the right,
 * or up to begin placing a new row.
 */
public class WallBuilder
{

    /**
     * The {@link Brick}s placed on the {@link Wall} being built.
     */
    private final List<Brick> bricks = new ArrayList<>();

    /**
     * The current pointer on the {@link Wall} being built.
     */
    private Position currentPointer = new Position(0, 0);

    /**
     * The number of bricks of size four placed on the {@link Wall} being built.
     */
    private int fourPieces = 0;

    /**
     * The number of bricks of size two placed on the {@link Wall} being built.
     */
    private int twoPieces = 0;

    /**
     * The number of bricks of size one placed on the {@link Wall} being built.
     */
    private int onePieces = 0;

    /**
     * Moves the {@code current pointer} up {@code 1} row to begin a new row of bricks.
     */
    public void up()
    {
        currentPointer = new Position(0, this.currentPointer.y + 1);
    }

    /**
     * Moves the {@code current pointer} {@code distance} dots to the right.
     *
     * @param distance The number of dots to move the {@code current pointer} to the right.
     */
    public void move(int distance)
    {
        currentPointer = this.currentPointer.right(distance);
    }

    /**
     * Places a brick of the provided {@code length} at the {@code current pointer}.
     *
     * @param length The length of the brick to place at the {@code current pointer}.
     */
    public void place(int length)
    {
        bricks.add(new Brick(length, currentPointer));
        currentPointer = this.currentPointer.right(length);

        if (length == 4)
            fourPieces++;
        if (length == 2)
            twoPieces++;
        if (length == 1)
            onePieces++;
    }

    /**
     * Builds a concrete {@link Wall} from the bricks places through the {@link WallBuilder}.
     *
     * @return The resulting instance of {@link Wall},
     */
    public Wall build()
    {
        return new Wall(bricks, fourPieces, twoPieces, onePieces);
    }

    /**
     * Returns the {@code current pointer} where new bricks are placed.
     *
     * @return the {@code current pointer} where new bricks are placed.
     * @see WallBuilder#place(int)
     */
    public Position getCurrentPointer()
    {
        return currentPointer;
    }
}
