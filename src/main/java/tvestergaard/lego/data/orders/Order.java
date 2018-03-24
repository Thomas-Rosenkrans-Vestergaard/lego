package tvestergaard.lego.data.orders;

import tvestergaard.lego.data.members.Member;
import tvestergaard.lego.logic.building.House;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Represents a purchase of bricks used to make a {@link House} made by some {@link Member}.
 */
public class Order
{

    /**
     * The unique id of the {@link Order}.
     */
    private final int id;

    /**
     * The {@link Member} who placed the {@link Order}.
     */
    private final Member member;

    /**
     * The width of the {@link House} that the bricks were ordered for.
     */
    private final int width;

    /**
     * The height of the {@link House} that the bricks were ordered for.
     */
    private final int height;

    /**
     * The depth of the {@link House} that the bricks were ordered for.
     */
    private final int depth;

    /**
     * A json string representing the {@link House} that the bricks were ordered for.
     */
    private final String specification;

    /**
     * The {@link Status} of the {@link Order}.
     */
    private final Status status;

    /**
     * The time and date at which the {@link Order} was created.
     */
    private final Timestamp createdAt;

    /**
     * The time and date at which the {@link Order} was last updated.
     */
    private final Timestamp updatedAt;

    /**
     * The time and date at which the {@link Order} was shipped to the {@link Member} who placed the {@link Order}.
     */
    private final Timestamp shippedAt;

    /**
     * Creates a new {@link Order}.
     *
     * @param id            The unique id of the {@link Order}.
     * @param member        The {@link Member} who placed the {@link Order}.
     * @param width         The width of the {@link House} that the bricks were ordered for.
     * @param height        The height of the {@link House} that the bricks were ordered for.
     * @param depth         The depth of the {@link House} that the bricks were ordered for.
     * @param specification A json string representing the {@link House} that the bricks were ordered for.
     * @param status        The {@link Status} of the {@link Order}.
     * @param createdAt     The time and date at which the {@link Order} was created.
     * @param updatedAt     The time and date at which the {@link Order} was last updated.
     * @param shippedAt     The time and date at which the {@link Order} was shipped to the {@link Member} who placed the {@link Order}.
     */
    public Order(int id, Member member, int width, int height, int depth, String specification, Status status,
                 Timestamp createdAt,
                 Timestamp updatedAt,
                 Timestamp shippedAt)
    {
        this.id = id;
        this.member = member;
        this.width = width;
        this.height = height;
        this.depth = depth;
        this.specification = specification;
        this.status = status;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.shippedAt = shippedAt;
    }

    /**
     * Returns the unique id of the {@link Order}.
     *
     * @return The unique id of the {@link Order}.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Returns the {@link Member} who placed the {@link Order}.
     *
     * @return The {@link Member} who placed the {@link Order}.
     */
    public Member getMember()
    {
        return this.member;
    }

    /**
     * Returns the width of the {@link House} that the bricks were ordered for.
     *
     * @return The width of the {@link House} that the bricks were ordered for.
     */
    public int getWidth()
    {
        return this.width;
    }

    /**
     * Returns the height of the {@link House} that the bricks were ordered for.
     *
     * @return The height of the {@link House} that the bricks were ordered for.
     */
    public int getHeight()
    {
        return this.height;
    }

    /**
     * Returns the depth of the {@link House} that the bricks were ordered for.
     *
     * @return The depth of the {@link House} that the bricks were ordered for.
     */
    public int getDepth()
    {
        return this.depth;
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

    /**
     * Returns the {@link Status} of the {@link Order}.
     *
     * @return The {@link Status} of the {@link Order}.
     */
    public Status getStatus()
    {
        return this.status;
    }

    /**
     * Returns the time and date at which the {@link Order} was created.
     *
     * @return The time and date at which the {@link Order} was created.
     */
    public Timestamp getCreatedAt()
    {
        return this.createdAt;
    }

    /**
     * Returns the time and date at which the {@link Order} was last updated.
     *
     * @return The time and date at which the {@link Order} was last updated.
     */
    public Timestamp getUpdatedAt()
    {
        return this.updatedAt;
    }

    /**
     * Returns the time and date at which the {@link Order} was shipped to the {@link Member} who placed the {@link Order}.
     *
     * @return The time and date at which the {@link Order} was shipped to the {@link Member} who placed the {@link Order}.
     */
    public Timestamp getShippedAt()
    {
        return this.shippedAt;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Order)) return false;
        Order order = (Order) o;
        return id == order.id;
    }

    @Override public int hashCode()
    {
        return Objects.hash(id);
    }

    @Override public String toString()
    {
        return "Order{" +
                "id=" + id +
                ", member=" + member +
                ", width=" + width +
                ", height=" + height +
                ", depth=" + depth +
                ", specification='" + specification + '\'' +
                ", status=" + status +
                '}';
    }
}
