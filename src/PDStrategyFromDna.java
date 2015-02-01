public class PDStrategyFromDna extends PDStrategy
{
    static class MovePair {
	public Move own, opp;
	MovePair(Move own, Move opp)
	{ 
	    this.own = own;
	    this.opp = opp;
	}

	public int toInt()
	{
	    char ownCh, oppCh;
	    ownCh = own.toChar();
	    oppCh = opp.toChar();

	    return 2*(ownCh-'C') + (oppCh-'C'); // between 0 and 3
	}
    }

    public static final int MEMORY_SIZE = 1;
    public static final int DNA_LENGTH = ((1 << 2*MEMORY_SIZE+2)-1)/3; // |encoding| == (4^(MEM_SIZE+1)-1)/3

    protected String movesTable;
    protected CircularQueue<MovePair> historyQueue = 
	new CircularQueue<MovePair>(MovePair.class, MEMORY_SIZE);
    
    public PDStrategyFromDna(String encoding)
    {
	// TODO: ensure encoding has right size according to memory
	this.movesTable = encoding;
	assert(DNA_LENGTH == this.movesTable.length());
    }

    @Override
    public Move getNextMove()
    {
	Move nextMove = null;
	MovePair[] history = this.historyQueue.getWholeArray();

	int dnaPos = buildQuaternaryString(history);
	nextMove = new Move(this.movesTable.charAt(dnaPos));

	return nextMove;
    }

    // converts pairs of moves in history into an index treating them as a quaternary digits
    protected int buildQuaternaryString(MovePair history[])
    {
	int curMemSize = history.length;
	if (curMemSize == 0)
	    return 0; // no history means using the default move

	// build a quaternary number
	int i;
	StringBuilder sb  = new StringBuilder();
	for (i = 0; i < curMemSize; i++) { // from the oldest to the newest pair of moves
	    int digit = history[i].toInt();
	    sb.append(Integer.toString(digit));
	}
	// reverse so to get the freshent at the front
	sb.reverse();
	String res = sb.toString();

	// parse the quaternary number and add an offset according to the history size
	int qn =  Integer.parseInt(res, 4);
	int offset = ((1 << 2*curMemSize)-1)/3;
	
	return qn + offset;
	
    }

    @Override
    public void updateHistory(Move ownMove, Move opponentMove)
    {
	this.historyQueue.enqueue(new MovePair(ownMove, opponentMove));
    }

    public static void main(String args[])
    {
	PDStrategyFromDna firstD, titForTat;
	// NOTE: only one move pair of memory
	firstD = new PDStrategyFromDna("DCCCC");
	titForTat = new PDStrategyFromDna("CCDCD");
	/*
	  Explanation for tit for tat dna:
	   0 history ->           C
	   Last move pair: C,C -> C
	   Last move pair: C,D -> D
	   Last move pair: D,C -> C
	   Last move pair: D,D -> D
	*/

	RPD.play(firstD, titForTat);
	
    }

}

