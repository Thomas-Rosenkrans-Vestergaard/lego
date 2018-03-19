package tvestergaard.lego.logic;

import org.mindrot.jbcrypt.BCrypt;
import tvestergaard.lego.database.members.*;

public class MemberFacade
{

    private static MemberDAO memberDAO = new MysqlMemberDAO(ProductionConnection.source());

    public static Member login(String email, String password)
    {
        Member member = memberDAO.findByEmail(email);

        if (member == null)
            return null;

        if (!BCrypt.checkpw(password, member.getPassword()))
            return null;

        return member;
    }

    public static Member register(String email, String password) throws InvalidEmailException, EmailCollisionException
    {
        MemberBuilder builder = new MemberBuilder();
        builder.setEmail(email);
        builder.setPassword(BCrypt.hashpw(password, BCrypt.gensalt()));
        builder.setRole(Role.MEMBER);

        return memberDAO.create(builder);
    }
}
