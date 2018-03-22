package tvestergaard.lego.logic.building;

import java.util.ArrayList;
import java.util.List;

public class Wall
{

    private final List<Brick> bricks;
    private       int         fourPieces;
    private       int         twoPieces;
    private       int         onePieces;

    public Wall(List<Brick> bricks, int fourPieces, int twoPieces, int onePieces)
    {
        this.bricks = bricks;
        this.fourPieces = fourPieces;
        this.twoPieces = twoPieces;
        this.onePieces = onePieces;
    }

    public List<Brick> getBricks()
    {
        return new ArrayList<>(this.bricks);
    }

    public int getFourPieces()
    {
        return this.fourPieces;
    }

    public int getTwoPieces()
    {
        return this.twoPieces;
    }

    public int getOnePieces()
    {
        return this.onePieces;
    }
}
