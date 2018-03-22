package tvestergaard.lego.logic.building;

import java.util.Objects;

/**
 * Defines the blueprint for a {@link House}.
 */
public class HouseSpecification
{

    /**
     * The dimensions of the {@link HouseSpecification}.
     */
    public final Cube dimensions;

    /**
     * The {@link Door} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Door} when this field is {@code null}.
     */
    public final Door door;

    /**
     * The {@link Window} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Window} when this field is {@code null}.
     */
    public final Window window;

    /**
     * Creates a new {@link HouseSpecification} using the provided components.
     *
     * @param dimensions The dimensions of the {@link HouseSpecification}.
     * @param door       The {@link Door} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Door} when this field is {@code null}.
     * @param window     The {@link Window} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Window} when this field is {@code null}.
     */
    public HouseSpecification(Cube dimensions, Door door, Window window)
    {
        this.dimensions = dimensions;
        this.door = door;
        this.window = window;
    }

    /**
     * Returns the dimensions of the {@link HouseSpecification}.
     *
     * @return The dimensions of the {@link HouseSpecification}.
     */
    public Cube getDimensions()
    {
        return this.dimensions;
    }

    /**
     * Returns the {@link Door} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Door} when this field is {@code null}.
     *
     * @return The {@link Door} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Door} when this field is {@code null}.
     */
    public Door getDoor()
    {
        return this.door;
    }

    /**
     * Returns the {@link Window} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Window} when this field is {@code null}.
     *
     * @return The {@link Window} of the {@link HouseSpecification}. The {@link HouseSpecification} has no {@link Window} when this field is {@code null}.
     */
    public Window getWindow()
    {
        return this.window;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof HouseSpecification)) return false;
        HouseSpecification house = (HouseSpecification) o;
        return Objects.equals(dimensions, house.dimensions) &&
               Objects.equals(door, house.door) &&
               Objects.equals(window, house.window);
    }

    @Override public int hashCode()
    {
        return Objects.hash(dimensions, door, window);
    }

    @Override public String toString()
    {
        return "House{" +
               "dimensions=" + dimensions +
               ", door=" + door +
               ", window=" + window +
               '}';
    }
}
