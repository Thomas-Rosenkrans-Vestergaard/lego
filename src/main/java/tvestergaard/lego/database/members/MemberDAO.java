package tvestergaard.lego.database.members;

public interface MemberDAO
{

    Member findByEmail(String email);

    Member create(MemberBuilder builder) throws EmailCollisionException;
}
