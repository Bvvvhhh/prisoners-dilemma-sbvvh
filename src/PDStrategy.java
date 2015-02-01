interface Strategy
{
    // returns next move from the player
    Move getNextMove();

    // informs the player about its score
    void takeScore(int s);

    // returns current score
    int getScore();
    
    // update the history of the player with its own and the opponent's moves
    void updateHistory(Move ownMove, Move opponentMove);
}

public abstract class PDStrategy implements Strategy
{
    protected int score = 0;
    

    @Override
    public void takeScore(int s)  {  this.score += s; }

    @Override
    public int getScore() { return this.score; }
    
}
