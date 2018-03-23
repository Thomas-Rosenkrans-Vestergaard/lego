package tvestergaard.lego.logic.geometry;

import java.util.Objects;

/**
 * Represents an area in 2-dimensional space.
 */
public class Square
{

    /**
     * The {@code width} of the {@link Square}.
     */
    public final int width;

    /**
     * The {@code height} of the {@link Square}.
     */
    public final int height;

    /**
     * Creates a new {@link Square} using the provided {@code width} and {@code height} component.
     *
     * @param width  The {@code width} of the {@link Square}.
     * @param height The {@code height} of the {@link Square}.
     */
    public Square(int width, int height)
    {
        this.width = width;
        this.height = height;
    }

    /**
     * Creates a new {@link Square} using the provided {@code width} and {@code height} component.
     *
     * @param width  The {@code width} of the {@link Square}.
     * @param height The {@code height} of the {@link Square}.
     */
    public static Square of(int width, int height)
    {
        return new Square(width, height);
    }

    /**
     * Returns the {@code width} of the {@link Square}.
     *
     * @return The {@code width} of the {@link Square}.
     */
    public int getWidth()
    {
        return this.width;
    }

    /**
     * Returns the {@code height} of the {@link Square}.
     *
     * @return The {@code height} of the {@link Square}.
     */
    public int getHeight()
    {
        return this.height;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Square)) return false;
        Square area = (Square) o;
        return width == area.width &&
               height == area.height;
    }

    @Override public int hashCode()
    {
        return Objects.hash(width, height);
    }

    @Override public String toString()
    {
        return "Area{" +
               "width=" + width +
               ", height=" + height +
               '}';
    }
}
