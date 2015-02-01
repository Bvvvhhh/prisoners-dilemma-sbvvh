/*
  RPD.java: contains code related to playing Repeated Prisoner's Dilemma
 */


/* Class Move: a move (or action) in a game */
class Move
{
    // mnemonic name of the Move (e.g 'C' for "Cooperate")
    private char c;

    Move(char c)
    {
	this.c = c;
    }
    
    public char toChar()
    {
	return c;
    }
}

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

abstract class PDStrategy implements Strategy
{
    protected int score = 0;
    

    @Override
    public void takeScore(int s)  {  this.score += s; }

    @Override
    public int getScore() { return this.score; }
    
    @Override
    public void updateHistory(Move ownMove, Move opponentMove)  {  }
}

class PDStrategyFromDna extends PDStrategy
{
    public static final int MEMORY_SIZE = 1;

    protected String movesTable;
    protected CircularQueue<Move> history = new CircularQueue<Move>(Move.class, MEMORY_SIZE);
    
    public PDStrategyFromDna(String encoding)
    {
	// TODO: ensure encoding has right size according to memory
	this.movesTable = encoding;
	assert((1 << MEMORY_SIZE)-1 == encoding.length()); // checks that |encoding| == 2^(MEM_SIZE)-1
    }

    @Override
    public Move getNextMove()
    {
	return null;
    }

    

}


class Game
{
    public static Score play(PDStrategy player1, PDStrategy player2) throws Exception
    {
	throw new Exception("Don't use me, please. And wash your hands afterwards");
	// return null;
    }
}

class Score
{
    public int p1;
    public int p2;

    Score(int p1, int p2)
    {
	this.p1 = p1;
	this.p2 = p2;
    }

    @Override
    public String toString()
    {
	return "(" + p1 + " " + p2 + ")";
    }

}


// Prisoners Dilemma game
class PD extends Game
{
    public static final Move C = new Move('C');
    public static final Move D = new Move('D');
    
    public static final Score gameMatrix[][] = new Score[2][2]; // use two game matrices
    static {
	final int C = 0;
	final int D = 1;

	gameMatrix[C][C] = new Score(3,3);
	gameMatrix[D][D] = new Score(1,1);
	gameMatrix[D][C] = new Score(5,0);
	gameMatrix[C][D] = new Score(0,5);
    }
    

    public static Score play(PDStrategy player1, PDStrategy player2)
    {
	// players should be notified about the other player's move and record the history somehow
	Move m1 = player1.getNextMove();
	Move m2 = player2.getNextMove();

	// get rewards
	Score score = getScoreEntry(m1, m2);
	
	// give scores
	player1.takeScore(score.p1);
	player2.takeScore(score.p2);

	// inform about history
	player1.updateHistory(m1, m2);
	player2.updateHistory(m2, m1);

	return score;
    }

    protected static Score getScoreEntry(Move m1, Move m2)
    {
	char c1, c2;
	c1 = m1.toChar();
	c2 = m2.toChar();

	return gameMatrix[c1-'C'][c2-'C'];
    }
}

// Repeated Prisoners Dilemma
public class RPD extends Game
{

    public static Score play(PDStrategy player1, PDStrategy player2) 
    {
	return PD.play(player1, player2); // XXX: for now only using the stage game
    }

    /*    public static void main(String args[])
    {
	PDStrategy p1, p2;
	p1 = new PDStrategy();
	p2 = new PDStrategy();

	System.out.println("Stuff is like you know this score: " + RPD.play(p1, p2));
        } */
}
