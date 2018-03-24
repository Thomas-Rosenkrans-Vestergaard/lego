package tvestergaard.lego.data.members;

import java.sql.Timestamp;
import java.util.Objects;

/**
 * Represents a member in the application.
 */
public class Member
{

    /**
     * The unique id of the {@link Member}.
     */
    private final int id;

    /**
     * The unique email of the {@link Member}.
     */
    private final String email;

    /**
     * The hashed password of the {@link Member}.
     */
    private final String password;

    /**
     * The role of the {@link Member}.
     */
    private final Role role;

    /**
     * The {@code Timestamp} when the {@link Member} was created.
     */
    private final Timestamp createdAt;

    /**
     * The {@code Timestamp} when the {@link Member} was last updated.
     */
    private final Timestamp updatedAt;

    /**
     * Creates a new {@link Member}.
     *
     * @param id        The unique id of the {@link Member}.
     * @param email     The unique email of the {@link Member}.
     * @param password  The hashed password of the {@link Member}.
     * @param role      The role of the {@link Member}.
     * @param createdAt The {@code Timestamp} when the {@link Member} was created.
     * @param updatedAt The {@code Timestamp} when the {@link Member} was last updated.
     */
    public Member(int id, String email, String password, Role role, Timestamp createdAt, Timestamp updatedAt)
    {
        this.id = id;
        this.email = email;
        this.password = password;
        this.role = role;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    /**
     * Returns the unique id of the {@link Member}.
     *
     * @return The unique id of the {@link Member}.
     */
    public int getId()
    {
        return this.id;
    }

    /**
     * Returns the unique email of the {@link Member}.
     *
     * @return The unique email of the {@link Member}.
     */
    public String getEmail()
    {
        return this.email;
    }

    /**
     * Returns the hashed password of the {@link Member}.
     *
     * @return The hashed password of the {@link Member}.
     */
    public String getPassword()
    {
        return this.password;
    }

    /**
     * Returns the role of the {@link Member}.
     *
     * @return The role of the {@link Member}.
     */
    public Role getRole()
    {
        return this.role;
    }

    /**
     * Returns the {@code Timestamp} when the {@link Member} was created.
     *
     * @return The {@code Timestamp} when the {@link Member} was created.
     */
    public Timestamp getCreatedAt()
    {
        return this.createdAt;
    }

    /**
     * Returns the {@code Timestamp} when the {@link Member} was last updated.
     *
     * @return The {@code Timestamp} when the {@link Member} was last updated.
     */
    public Timestamp getUpdatedAt()
    {
        return this.updatedAt;
    }

    /**
     * Checks if the provided {@link Member} and {@code this} has the same id.
     *
     * @param member The {@link Member} to compare {@code this} to.
     * @return {@code true} when the provided {@link Member} and {@code this} has the same id.
     */
    public boolean is(Member member)
    {
        return id == member.id;
    }

    @Override public boolean equals(Object o)
    {
        if (this == o) return true;
        if (!(o instanceof Member)) return false;
        Member member = (Member) o;
        return id == member.id &&
                Objects.equals(email, member.email) &&
                Objects.equals(password, member.password) &&
                role == member.role &&
                Objects.equals(createdAt, member.createdAt) &&
                Objects.equals(updatedAt, member.updatedAt);
    }

    @Override public int hashCode()
    {
        return Objects.hash(id, email, password, role, createdAt, updatedAt);
    }
}
