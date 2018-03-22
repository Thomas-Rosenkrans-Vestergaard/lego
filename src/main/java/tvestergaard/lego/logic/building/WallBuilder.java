package tvestergaard.lego.logic.building;

import java.util.ArrayList;
import java.util.List;

public class WallBuilder
{

    /**
     * The
     */
    private final List<Brick> bricks = new ArrayList<>();
    private Position position = new Position(0, 0);
    private int      counter4;
    private int      counter2;
    private int      counter1;

    /**
     * Moves the pointer to the next row above the current pointer.
     *
     * @param distance
     */
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
            counter4++;
        if (length == 2)
            counter2++;
        if (length == 1)
            counter1++;
    }

    public Wall build()
    {
        return new Wall(bricks, counter4, counter2, counter1);
    }

    public Position getCurrentPosition()
    {
        return position;
    }
}
