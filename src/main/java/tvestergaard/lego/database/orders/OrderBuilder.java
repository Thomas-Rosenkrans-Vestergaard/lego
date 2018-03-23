package tvestergaard.lego.database.orders;

import tvestergaard.lego.database.members.Member;

public class OrderBuilder
{
    private Member member;
    private int    width;
    private int    height;
    private int    depth;
    private String specifications;
    private Status status;

    public OrderBuilder(Member member, int width, int height, int depth, String specifications, Status status)
    {
        this.member = member;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.specifications = specifications;
        this.status = status;
    }

    public OrderBuilder(Order order)
    {
        this.member = order.getMember();
        this.width = order.getWidth();
        this.height = order.getHeight();
        this.depth = order.getDepth();
        this.specifications = order.getSpecifications();
        this.status = order.getStatus();
    }

    public Member getMember()
    {
        return this.member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    public int getWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    public int getHeight()
    {
        return this.height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    public int getDepth()
    {
        return this.depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    public String getSpecifications()
    {
        return this.specifications;
    }

    public void setSpecifications(String specifications)
    {
        this.specifications = specifications;
    }

    public Status getStatus()
    {
        return this.status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }
}
