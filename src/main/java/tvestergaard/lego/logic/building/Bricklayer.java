package tvestergaard.lego.logic.building;

public interface Bricklayer
{

    /**
     * Creates the {@link House} specified from the provided {@link HouseSpecification}.
     *
     * @param specifications The specifications for the {@link House} to build.
     * @return The {@link House} created from the provided {@link HouseSpecification}.
     * @throws IllegalHouseDimensionsException When the dimensions of the provided {@link HouseSpecification} is invalid.
     * @throws IllegalWindowException          When the placement of the {@link Window} specified in the provided
     *                                         {@link HouseSpecification} is invalid.
     * @throws IllegalDoorException            When the placement of the {@link Door} specified in the provided
     *                                         {@link HouseSpecification} is invalid.
     * @throws IllegalCollisionException       When the placement of the {@link Door} or {@link Window} specified in the provided
     *                                         {@link HouseSpecification} collide.
     */
    House lay(HouseSpecification specifications) throws
            IllegalHouseDimensionsException,
            IllegalWindowException,
            IllegalDoorException,
            IllegalCollisionException;
}
