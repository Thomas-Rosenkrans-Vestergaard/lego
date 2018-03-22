package tvestergaard.lego.logic.building;

import java.util.ArrayList;
import java.util.List;

/**
 * Helper class for creating instances of {@link Wall}.
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
    private Position position = new Position(0, 0);

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

    public void up(int distance)
    {
        position = new Position(0, this.position.y + distance);
    }

    public void up()
    {
        up(1);
    }

    public void move(int distance)
    {
        position = this.position.right(distance);
    }

    public void place(int length)
    {
        if (length != 4 && length != 2 && length != 1)
            throw new UnsupportedOperationException();

        bricks.add(new Brick(length, position));

        position = this.position.right(length);

        if (length == 4)
            fourPieces++;
        if (length == 2)
            twoPieces++;
        if (length == 1)
            onePieces++;
    }

    public Wall build()
    {
        return new Wall(bricks, fourPieces, twoPieces, onePieces);
    }

    public Position getCurrentPosition()
    {
        return position;
    }
}
