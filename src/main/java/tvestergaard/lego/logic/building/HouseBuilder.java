package tvestergaard.lego.logic.building;

public interface HouseBuilder
{

    /**
     * Builds the {@link House] using the provided width, height, depth and the provided door and window.
     *
     * @param width  The width of the house to build.
     * @param height The height of the house to build.
     * @param depth  The depth of the house to build.
     * @param door   The {@link Door} to place on the house. When {@code null} is provided, no {@link Door} is placed on
     *               the {@link House}.
     * @param window The {@link Window} to place on the house. When {@code null} is provided, no {@link Window} is placed
     *               on the {@link House}.
     * @return The {@link House] built using the provided parameters.
     */
    House build(int width, int height, int depth, Door door, Window window);
}
