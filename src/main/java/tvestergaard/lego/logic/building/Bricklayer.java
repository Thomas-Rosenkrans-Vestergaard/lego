package tvestergaard.lego.logic.building;

public interface Bricklayer
{

    /**
     * Creates the {@link House} using the details from the provided {@link HouseSpecification}.
     *
     * @param specifications The specifications for the {@link House} to build.
     * @return The {@link House} created from the provided {@link HouseSpecification}.
     * @throws BricklayerException When the provided {@link HouseSpecification} cannot be built.
     */
    House lay(HouseSpecification specifications) throws BricklayerException;
}
