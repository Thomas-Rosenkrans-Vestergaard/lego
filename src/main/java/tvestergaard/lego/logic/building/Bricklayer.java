package tvestergaard.lego.logic.building;

public interface Bricklayer
{

    /**
     * Creates the {@link House} specified from the provided {@link HouseSpecification}.
     *
     * @param specifications The specifications for the {@link House} to build.
     * @return The {@link House} created from the provided {@link HouseSpecification}.
     */
    House lay(HouseSpecification specifications);
}
