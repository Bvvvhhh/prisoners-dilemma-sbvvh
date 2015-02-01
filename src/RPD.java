/*
  RPD.java: contains code related to playing Repeated Prisoner's Dilemma
 */


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

	// debugging output
	System.out.print("FirstD vs TitForTat: (" + m1.toChar() + ", " + m2.toChar() + ")\t");

	// get rewards
	Score score = getScoreEntry(m1, m2);
	
	// give scores
	player1.takeScore(score.p1);
	player2.takeScore(score.p2);

	System.out.println("Reward is: (" + score.p1 + ", " + score.p2 + ")");

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
    public static final int NUM_ROUNDS = 5;

    // returns the score of the last game
    public static Score play(PDStrategy player1, PDStrategy player2) 
    {
	Score s = null;
	for (int i = 1; i <= NUM_ROUNDS; i++)
	    s = PD.play(player1,player2);

	return s;
    }

}
