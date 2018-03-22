package tvestergaard.lego.logic.building;

public class House
{

    private final Wall front;
    private final Wall back;
    private final Wall right;
    private final Wall left;

    public House(Wall front, Wall back, Wall right, Wall left)
    {
        this.front = front;
        this.back = back;
        this.right = right;
        this.left = left;
    }

    public Wall getFront()
    {
        return this.front;
    }

    public Wall getBack()
    {
        return this.back;
    }

    public Wall getRight()
    {
        return this.right;
    }

    public Wall getLeft()
    {
        return this.left;
    }

    public int getFourPieces()
    {
        return front.getFourPieces() +
               back.getFourPieces() +
               left.getFourPieces() +
               right.getFourPieces();
    }


    public int getTwoPieces()
    {
        return front.getTwoPieces() +
               back.getTwoPieces() +
               left.getTwoPieces() +
               right.getTwoPieces();
    }


    public int getOnePieces()
    {
        return front.getOnePieces() +
               back.getOnePieces() +
               left.getOnePieces() +
               right.getOnePieces();
    }
}
