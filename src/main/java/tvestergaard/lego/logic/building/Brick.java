package tvestergaard.lego.logic.building;

import java.util.Objects;

/**
 * Represents some {@link Brick} in a {@link Wall}.
 */
public class Brick
{

    /**
     * The length of the {@link Brick}.
     */
    public final int length;

    /**
     * The {@link Position} of the {@link Brick} in some {@link WallBuilder}.
     */
    public final Position position;

    /**
     * Creates a new {@link Brick}.
     *
     * @param length The length of the {@link Brick}.
     */
    public Brick(int length, Position position)
    {
        this.length = length;
        this.position = position;
    }

    /**
     * Returns the length of the {@link Brick}.
     *
     * @return The length of the {@link Brick}.
     */
    public int getLength()
    {
        return this.length;
    }

    /**
     * Returns the {@link Position} of the {@link Brick} in some {@link WallBuilder}.
     *
     * @return The {@link Position} of the {@link Brick} in some {@link WallBuilder}.
     */
    public Position getPosition()
    {
        return this.position;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Brick)) return false;
        Brick brick = (Brick) o;
        return length == brick.length &&
               Objects.equals(position, brick.position);
    }

    @Override public int hashCode()
    {
        return Objects.hash(length, position);
    }

    @Override public String toString()
    {
        return "Brick{" +
               "length=" + length +
               ", position=" + position +
               '}';
    }
}
