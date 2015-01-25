import java.util.Arrays;

public class PrisonersDilemmaStrategy
{
    public final static int MEMORY = 2;

    private String movesTable[];

    public PrisonersDilemmaStrategy(String encoding[])
    {
	// TODO: ensure encoding has right size according to memory
	
	this.movesTable = Arrays.copyOf(encoding, encoding.length);
	
    }
}
