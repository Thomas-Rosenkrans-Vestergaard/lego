package tvestergaard.lego.data.orders;

import tvestergaard.lego.data.members.Member;
import tvestergaard.lego.logic.building.House;

import java.sql.Timestamp;

/**
 * Helper for mutating or creating {@link Order} instances.
 */
public class OrderBuilder
{

    /**
     * The {@link Member} who placed the order being mutated or created.
     */
    private Member member;

    /**
     * The width of the {@link House} of the order being mutated or created.
     */
    private int width;

    /**
     * The height of the {@link House} of the order being mutated or created.
     */
    private int height;

    /**
     * The depth of the {@link House} of the order being mutated or created.
     */
    private int depth;

    /**
     * A json string representing the {@link House} that the bricks were ordered for.
     */
    private String specification;

    /**
     * The depth of the order being mutated or created.
     */
    private Status status;

    /**
     * The shipping date of the {@link House} of the order being mutated or created.
     */
    private Timestamp shippedAt;

    /**
     * Creates a new {@link OrderBuilder}.
     *
     * @param member        The {@link Member} who placed the order being mutated or created.
     * @param width         The width of the {@link House} of the order being mutated or created.
     * @param height        The height of the {@link House} of the order being mutated or created.
     * @param depth         The depth of the {@link House} of the order being mutated or created.
     * @param specification A json string representing the {@link House} that the bricks were ordered for.
     * @param status        The depth of the order being mutated or created.
     * @param shippedAt     The shipping date of the {@link House} of the order being mutated or created.
     */
    public OrderBuilder(Member member, int width, int height, int depth, String specification, Status status, Timestamp shippedAt)
    {
        this.member = member;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.specification = specification;
        this.status = status;
        this.shippedAt = shippedAt;
    }

    /**
     * Creates a new {@link OrderBuilder} for mutating a {@link Order}.
     *
     * @param order The order to create a new {@link OrderBuilder} from.
     */
    public OrderBuilder(Order order)
    {
        this.member = order.getMember();
        this.width = order.getWidth();
        this.height = order.getHeight();
        this.depth = order.getDepth();
        this.specification = order.getSpecification();
        this.status = order.getStatus();
        this.shippedAt = order.getShippedAt();
    }

    /**
     * Returns the {@link Member} who placed the order being mutated or created.
     *
     * @return The {@link Member} who placed the order being mutated or created.
     */
    public Member getMember()
    {
        return this.member;
    }

    public void setMember(Member member)
    {
        this.member = member;
    }

    /**
     * Returns the width of the {@link House} of the order being mutated or created.
     *
     * @return The width of the {@link House} of the order being mutated or created.
     */
    public int getWidth()
    {
        return this.width;
    }

    public void setWidth(int width)
    {
        this.width = width;
    }

    /**
     * Returns the height of the {@link House} of the order being mutated or created.
     *
     * @return The height of the {@link House} of the order being mutated or created.
     */
    public int getHeight()
    {
        return this.height;
    }

    public void setHeight(int height)
    {
        this.height = height;
    }

    /**
     * Returns the depth of the {@link House} of the order being mutated or created.
     *
     * @return The depth of the {@link House} of the order being mutated or created.
     */
    public int getDepth()
    {
        return this.depth;
    }

    public void setDepth(int depth)
    {
        this.depth = depth;
    }

    /**
     * Returns a json string representing the {@link House} that the bricks were ordered for.
     *
     * @return A json string representing the {@link House} that the bricks were ordered for.
     */
    public String getSpecification()
    {
        return this.specification;
    }

    public void setSpecification(String specification)
    {
        this.specification = specification;
    }

    /**
     * Returns the depth of the order being mutated or created.
     *
     * @return The depth of the order being mutated or created.
     */
    public Status getStatus()
    {
        return this.status;
    }

    public void setStatus(Status status)
    {
        this.status = status;
    }

    /**
     * Returns the shipping date of the {@link House} of the order being mutated or created.
     *
     * @return The shipping date of the {@link House} of the order being mutated or created.
     */
    public Timestamp getShippedAt()
    {
        return this.shippedAt;
    }

    public void setShippedAt(Timestamp shippedAt)
    {
        this.shippedAt = shippedAt;
    }
}
