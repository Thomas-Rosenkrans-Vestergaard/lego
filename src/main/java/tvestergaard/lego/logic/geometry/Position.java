package tvestergaard.lego.logic.geometry;

/**
 * Represents a coordinate with an {@code x} and {@code y} component.
 */
public class Position
{

    /**
     * The {@code x} component of the {@link Position}.
     */
    public final int x;

    /**
     * The {@code y} component of the {@link Position}.
     */
    public final int y;

    /**
     * Creates a new {@link Position} using the provided {@code x} and {@code y} component.
     *
     * @param x The {@code x} component of the {@link Position}.
     * @param y The {@code y} component of the {@link Position}.
     */
    public Position(int x, int y)
    {
        this.x = x;
        this.y = y;
    }

    /**
     * Creates a new {@link Position} using the provided {@code x} and {@code y} component.
     *
     * @param x The {@code x} component of the {@link Position}.
     * @param y The {@code y} component of the {@link Position}.
     */
    public static Position of(int x, int y)
    {
        return new Position(x, y);
    }

    /**
     * Returns the {@code x} component of the {@link Position}.
     *
     * @return The {@code x} component of the {@link Position}.
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Returns the {@code y} component of the {@link Position}.
     *
     * @return The {@code y} component of the {@link Position}.
     */
    public int getY()
    {
        return this.y;
    }

    /**
     * Returns a {@link Position} representing an offset of the provided {@code distance} up.
     *
     * @param distance The distance to offset.
     * @return The {@link Position} representing an offset of the provided {@code distance} up.
     */
    public Position up(int distance)
    {
        return new Position(x, y + distance);
    }

    /**
     * Returns a {@link Position} representing an offset of the provided {@code distance} down.
     *
     * @param distance The distance to offset.
     * @return The {@link Position} representing an offset of the provided {@code distance} down.
     */
    public Position down(int distance)
    {
        return new Position(x, y - distance);
    }

    /**
     * Returns a {@link Position} representing an offset of the provided {@code distance} to the left.
     *
     * @param distance The distance to offset.
     * @return The {@link Position} representing an offset of the provided {@code distance} to the left.
     */
    public Position left(int distance)
    {
        return new Position(x - distance, y);
    }

    /**
     * Returns a {@link Position} representing an offset of the provided {@code distance} to the right.
     *
     * @param distance The distance to offset.
     * @return The {@link Position} representing an offset of the provided {@code distance} to the right.
     */
    public Position right(int distance)
    {
        return new Position(x + distance, y);
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }

    @Override public String toString()
    {
        return "Position{" +
                "x=" + x +
                ", y=" + y +
                '}';
    }
}
