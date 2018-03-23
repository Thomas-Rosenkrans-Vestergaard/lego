package tvestergaard.lego.database.orders;

import tvestergaard.lego.database.members.Member;

public class Order
{

    private final int    id;
    private final Member member;
    private final int    width;
    private final int    height;
    private final int    depth;
    private final String specifications;
    private final Status status;

    public Order(int id, Member member, int width, int height, int depth, String specifications, Status status)
    {
        this.id = id;
        this.member = member;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.specifications = specifications;
        this.status = status;
    }

    public int getId()
    {
        return this.id;
    }

    public Member getMember()
    {
        return this.member;
    }

    public int getWidth()
    {
        return this.width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public int getDepth()
    {
        return this.depth;
    }

    public String getSpecifications()
    {
        return this.specifications;
    }

    public Status getStatus()
    {
        return this.status;
    }
}
