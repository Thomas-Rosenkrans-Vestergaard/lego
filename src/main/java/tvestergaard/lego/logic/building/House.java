package tvestergaard.lego.logic.building;

public class House
{

    private final WallBuilder front;
    private final WallBuilder back;
    private final WallBuilder right;
    private final WallBuilder left;

    public House(WallBuilder front, WallBuilder back, WallBuilder right, WallBuilder left)
    {
        this.front = front;
        this.back = back;
        this.right = right;
        this.left = left;
    }

    public WallBuilder getFront()
    {
        return this.front;
    }

    public WallBuilder getBack()
    {
        return this.back;
    }

    public WallBuilder getRight()
    {
        return this.right;
    }

    public WallBuilder getLeft()
    {
        return this.left;
    }
}
