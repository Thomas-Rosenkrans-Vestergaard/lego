package tvestergaard.lego.logic.geometry;

import java.util.Objects;

/**
 * Represents a square anchored by a position in two dimensions at the lower left corner of the square.
 */
public class PositionedSquare extends Square
{

    /**
     * The x-coordinate of the position anchoring the square at the lower left corner of the square.
     */
    public final int x;

    /**
     * The y-coordinate of the position anchoring the square at the lower left corner of the square.
     */
    public final int y;

    /**
     * Creates a new {@link PositionedSquare}.
     *
     * @param width    The width of the {@link PositionedSquare}.
     * @param height   The height of the {@link PositionedSquare}.
     * @param position The position anchoring the {@link PositionedSquare}.
     */
    public PositionedSquare(int width, int height, Position position)
    {
        this(width, height, position.x, position.y);
    }

    /**
     * Creates a new {@link PositionedSquare}.
     *
     * @param width  The width of the {@link PositionedSquare}.
     * @param height The height of the {@link PositionedSquare}.
     * @param x      The x-coordinate of the position anchoring the square at the lower left corner of the square.
     * @param y      The y-coordinate of the position anchoring the square at the lower left corner of the square.
     */
    public PositionedSquare(int width, int height, int x, int y)
    {
        super(width, height);
        this.x = x;
        this.y = y;
    }

    /**
     * Checks if the provided {@link Position} is contained within the {@link PositionedSquare}.
     *
     * @param position The {@link Position} to check if is contained.
     * @return {@code true} if the provided {@link Position} is contained within the {@link PositionedSquare}.
     */
    public boolean contains(Position position)
    {
        return position.x >= this.x &&
                position.y >= this.y &&
                position.x < this.x + width &&
                position.y < this.y + height;
    }

    /**
     * Checks if the provided {@link PositionedSquare} overlaps this object at any coordinate.
     *
     * @param other The {@link PositionedSquare} to check if overlaps.
     * @return {@code true} of the provided {@link PositionedSquare} overlaps this object at any coordinate.
     */
    public boolean overlaps(PositionedSquare other)
    {
        return this.x < other.x + other.width &&
                this.x + this.width > other.x &&
                this.y < other.y + other.height &&
                this.y + other.height > other.y;
    }

    /**
     * Returns the x-coordinate of the position anchoring the square at the lower left corner of the square.
     *
     * @return The x-coordinate of the position anchoring the square at the lower left corner of the square.
     */
    public int getX()
    {
        return this.x;
    }

    /**
     * Returns the y-coordinate of the position anchoring the square at the lower left corner of the square.
     *
     * @return The y-coordinate of the position anchoring the square at the lower left corner of the square.
     */
    public int getY()
    {
        return this.y;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof PositionedSquare)) return false;
        if (!super.equals(o)) return false;
        PositionedSquare that = (PositionedSquare) o;
        return x == that.x &&
                y == that.y;
    }

    @Override public int hashCode()
    {
        return Objects.hash(super.hashCode(), x, y);
    }

    @Override public String toString()
    {
        return "PositionedSquare{" +
                "x=" + x +
                ", y=" + y +
                ", width=" + width +
                ", height=" + height +
                '}';
    }
}
