package tvestergaard.lego.logic;

import org.mindrot.jbcrypt.BCrypt;
import tvestergaard.lego.database.members.*;

/**
 * Provides a simplified API for performing operations on members.
 */
public class MemberFacade
{

    /**
     * The {@link MemberDAO} to query when executing the methods on this instance.
     */
    private final MemberDAO memberDAO;

    /**
     * Creates a new {@link MemberFacade} using the provided {@link MemberDAO}.
     *
     * @param memberDAO The {@link MemberDAO} to use as the data source.
     */
    public MemberFacade(MemberDAO memberDAO)
    {
        this.memberDAO = memberDAO;
    }

    /**
     * Attempts to login to a member account using the provided {@code email} and {@code password}.
     *
     * @param email    The email address to log in with.
     * @param password The password to log in with.
     * @return The {@link Member} instance that was successfully logged in as. Returns {@code null} in case the provided
     * credentials were incorrect.
     */
    public Member login(String email, String password)
    {
        Member member = memberDAO.select(email);

        if (member == null)
            return null;

        if (!BCrypt.checkpw(password, member.getPassword()))
            return null;

        return member;
    }

    /**
     * Creates a new user account using the provided user details.
     *
     * @param email    The email address to use when creating the user account.
     * @param password The password to use when creating the user account.
     * @return The {@link Member} instance created from the provided credentials.
     * @throws InvalidEmailException   When the provided email address is considered invalid.
     * @throws EmailCollisionException When the provided email address is already in use within the application.
     */
    public Member register(String email, String password) throws InvalidEmailException, EmailCollisionException
    {
        MemberBuilder builder = new MemberBuilder(
                email,
                hash(password),
                Role.MEMBER
        );

        return memberDAO.create(builder);
    }

    /**
     * Hashes the provided {@code password}.
     *
     * @param password The password to hash.
     * @return The produces hash.
     */
    private String hash(String password)
    {
        return BCrypt.hashpw(password, BCrypt.gensalt());
    }
}
