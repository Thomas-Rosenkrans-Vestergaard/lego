package tvestergaard.lego.database.orders;

import tvestergaard.lego.database.members.Member;

public class OrderBuilder
{
    private Member member;
    private int    width;
    private int    height;
    private int    depth;
    private String specification;
    private Status status;

    public OrderBuilder(Member member, int width, int height, int depth, String specification, Status status)
    {
        this.member = member;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.specification = specification;
        this.status = status;
    }

    public OrderBuilder(Order order)
    {
        this.member = order.getMember();
        this.width = order.getWidth();
        this.height = order.getHeight();
        this.depth = order.getDepth();
        this.specification = order.getSpecification();
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

    public String getSpecification()
    {
        return this.specification;
    }

    public void setSpecification(String specification)
    {
        this.specification = specification;
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
