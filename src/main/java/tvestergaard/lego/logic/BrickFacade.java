package tvestergaard.lego.logic;

import tvestergaard.lego.logic.building.*;
import tvestergaard.lego.logic.geometry.Cube;

public class BrickFacade
{

    /**
     * Builds a {@link House} using the provided specifications.
     *
     * @param width  The width of the {@link House} to build.
     * @param height The height of the {@link House} to build.
     * @param depth  The depth of the {@link House} to build.
     * @param door   The {@link Door} to place in the {@link House} to build. When {@code null}, no {@link Door} is
     *               placed on the {@link House}.
     * @param window The {@link Window} to place in the {@link House} to build. When {@code null}, no {@link Window} is
     *               placed on the {@link House}.
     * @return The built {@link House}.
     * @throws BricklayerException When the provided {@link HouseSpecification} cannot be built.
     */
    public static House build(int width, int height, int depth, Door door, Window window) throws BricklayerException
    {
        HouseSpecification specification = new HouseSpecification(Cube.of(width, height, depth), door, window);
        Bricklayer         layer         = new HalfPatternBricklayer();
        House              house         = layer.lay(specification);
        return house;
    }
}
