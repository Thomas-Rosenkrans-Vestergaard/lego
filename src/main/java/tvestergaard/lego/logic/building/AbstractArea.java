package tvestergaard.lego.logic.building;

public class AbstractArea implements Area
{

    protected final int height;
    protected final int width;

    public AbstractArea(int height, int width)
    {
        assert height >= 0;
        assert width >= 0;

        this.height = height;
        this.width = width;
    }

    @Override public int getHeight()
    {
        return this.height;
    }

    @Override public int getWidth()
    {
        return this.width;
    }
}
