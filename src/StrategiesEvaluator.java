import java.util.List;
import org.uncommons.watchmaker.framework.FitnessEvaluator;

/**
 * Evaluates strings and assigns a fitness score based on how they play RPD
 */
public class StrategiesEvaluator implements FitnessEvaluator<String>
{

    public StrategiesEvaluator()
    {

    }


    /**
     * Assigns one "penalty point" for every character in the candidate
     * string that differs from the corresponding position in the target
     * string.
     * @param candidate The evolved string to evaluate.
     * @param population {@inheritDoc}
     * @return The fitness score (how many characters are wrong) of the
     * specified string.
     */
    public double getFitness(String candidateDna,
                             List<? extends String> population)
    {
	return 1; // XXX: dummy return. To be replaced with score

	/* OLD CODE FROM ORIGINAL EXAMPLE:
        int errors = 0;
        for (int i = 0; i < candidate.length(); i++)
        {
            if (candidate.charAt(i) != targetString.charAt(i))
            {
                ++errors;
            }
        }
        return errors;

	*/
    }


    // NOTE should return true if the highest the fitness the better
    public boolean isNatural()
    {
        return true;
    }
}
