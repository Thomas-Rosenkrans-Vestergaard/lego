package tvestergaard.lego.logic.building;

public class Position
{

    public final int x;
    public final int y;

    public Position(int x, int y)
    {
        assert x >= 0;
        assert y >= 0;

        this.x = x;
        this.y = y;
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Position position = (Position) o;

        if (x != position.x) return false;
        return y == position.y;
    }

    @Override public int hashCode()
    {
        int result = x;
        result = 31 * result + y;
        return result;
    }
}
