package tvestergaard.lego.logic.building;

import java.util.ArrayList;
import java.util.List;

public class WallBuilder
{

    /**
     * The
     */
    private final List<Brick> bricks = new ArrayList<>();
    private       int         x      = 0;
    private       int         y      = 0;
    private int counter4;
    private int counter2;
    private int counter1;

    /**
     * Moves the pointer to the next row above the current pointer.
     *
     * @param distance
     */
    public void up(int distance)
    {
        x = 0;
        y += distance;
    }

    public void up()
    {
        up(1);
    }

    public void move(int distance)
    {
        x += distance;
    }

    public void place(int length)
    {
        if(length != 4 && length != 2 && length != 1)
            throw new UnsupportedOperationException();

        bricks.add(new Brick(length, new Position(x, y)));

        if(length == 4)
            counter4++;
        if(length == 2)
            counter2++;
        if(length == 1)
            counter1++;

    }
}
