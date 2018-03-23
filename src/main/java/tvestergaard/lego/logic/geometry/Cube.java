package tvestergaard.lego.logic.geometry;

import java.util.Objects;

/**
 * Represents a box in three-dimensional space.
 */
public class Cube
{

    /**
     * The {@code width} component of the {@link Cube}.
     */
    public final int width;

    /**
     * The {@code height} component of the {@link Cube}.
     */
    public final int height;

    /**
     * The {@code depth} component of the {@link Cube}.
     */
    public final int depth;

    /**
     * Creates a new {@link Cube} using the provided components.
     *
     * @param width  The {@code width} component of the {@link Cube}.
     * @param height The {@code height} component of the {@link Cube}.
     * @param depth  The {@code depth} component of the {@link Cube}.
     */
    public Cube(int width, int height, int depth)
    {
        this.width = width;
        this.height = height;
        this.depth = depth;
    }

    /**
     * Creates a new {@link Cube} using the provided components.
     *
     * @param width  The {@code width} component of the {@link Cube}.
     * @param height The {@code height} component of the {@link Cube}.
     * @param depth  The {@code depth} component of the {@link Cube}.
     */
    public static Cube of(int width, int height, int depth)
    {
        return new Cube(width, height, depth);
    }

    /**
     * Returns the {@code width} component of the {@link Cube}.
     *
     * @return The {@code width} component of the {@link Cube}.
     */
    public int getWidth()
    {
        return this.width;
    }

    /**
     * Returns  the {@code height} component of the {@link Cube}.
     *
     * @return The {@code height} component of the {@link Cube}.
     */
    public int getHeight()
    {
        return this.height;
    }

    /**
     * Returns the {@code depth} component of the {@link Cube}.
     *
     * @return The {@code depth} component of the {@link Cube}.
     */
    public int getDepth()
    {
        return this.depth;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Cube)) return false;
        Cube volume = (Cube) o;
        return width == volume.width &&
                height == volume.height &&
                depth == volume.depth;
    }

    @Override public int hashCode()
    {
        return Objects.hash(width, height, depth);
    }

    @Override public String toString()
    {
        return "Cube{" +
                "width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                '}';
    }
}
