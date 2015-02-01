/* Class Move: a move (or action) in a game */
public class Move
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

