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
    
    // update the history of the player with its own and the opponent's moves
    void updateHistory(Move ownMove, Move opponentMove);
}

class PDStrategy implements Strategy
{
    @Override
    public Move getNextMove() { return null; }
    @Override
    public void takeScore(int s)  {  }
    @Override
    public void updateHistory(Move ownMove, Move opponentMove)  {  }
}


class Game
{
    public static Score play(PDStrategy player1, PDStrategy player2) throws Exception
    {
	throw new Exception("Don't use me, please. And wash your hands afterwards");
	// return null;
    }
}


// Repeated Prisoners Dilemma
public class RPD extends Game
{

    public static Score play(PDStrategy player1, PDStrategy player2) throws Exception
    {
	return PD.play(player1, player2); // XXX: for now only using the stage game
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
}


// Prisoners Dilemma game
class PD extends Game
{
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

