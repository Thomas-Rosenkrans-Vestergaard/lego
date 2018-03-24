package tvestergaard.lego.logic.building;

import org.json.JSONArray;
import org.json.JSONObject;

public class HouseJsonConverter
{

    /**
     * Converts the provided {@link House} to a {@code json} formatted string.
     *
     * @param house The {@link House} to convert to a {@code json}.
     * @return The resulting {@code json} string.
     */
    public String convert(House house)
    {
        JSONObject root = new JSONObject()

                .put("width", house.width)
                .put("height", house.height)
                .put("depth", house.depth)

                .put("fours", house.getFourPieces())
                .put("twos", house.getTwoPieces())
                .put("ones", house.getOnePieces())

                .put("front", convert(house.getFront()))
                .put("back", convert(house.getBack()))
                .put("left", convert(house.getLeft()))
                .put("right", convert(house.getRight()));

        return root.toString();
    }

    /**
     * Converts the provided {@link Wall} into a {@code json} string.
     *
     * @param wall The {@link Wall} to convert into a {@code json} string.
     * @return The resulting {@code json} string.
     */
    private JSONObject convert(Wall wall)
    {
        JSONObject object = new JSONObject();

        object.put("fours", wall.getFourPieces());
        object.put("twos", wall.getTwoPieces());
        object.put("ones", wall.getOnePieces());

        JSONArray bricks = new JSONArray();
        int       index  = 0;
        for (Brick brick : wall.getBricks())
            bricks.put(index++, convert(brick));

        object.put("bricks", bricks);
        return object;
    }

    /**
     * Converts the provided {@link Brick} into a {@code json} string.
     *
     * @param brick The {@link Brick} to convert into a {@code json} string.
     * @return The resulting {@code json} string.
     */
    private JSONObject convert(Brick brick)
    {
        return new JSONObject().put("x", brick.position.x)
                               .put("y", brick.position.y)
                               .put("length", brick.length);
    }
}
