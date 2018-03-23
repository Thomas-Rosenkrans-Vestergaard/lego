package tvestergaard.lego.logic;

import tvestergaard.lego.logic.building.*;
import tvestergaard.lego.logic.building.IllegalHouseDimensionsException;
import tvestergaard.lego.logic.geometry.Cube;

public class BrickFacade
{

    public static House build(int width, int height, int depth, Door door, Window window) throws
            IllegalHouseDimensionsException,
            IllegalDoorException,
            IllegalWindowException,
            IllegalCollisionException
    {
        HouseSpecification specification = new HouseSpecification(Cube.of(width, height, depth), door, window);
        Bricklayer         layer         = new HalfPatternBricklayer();
        House              house         = layer.lay(specification);
        return house;
    }
}
