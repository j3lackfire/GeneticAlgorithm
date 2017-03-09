package SimulatedAnnealing;

import BinaryFatory.BinaryVariable;
import GASolutions.WorkingGA;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 3/9/2017.
 */
public class SASolution {
    public double[] mySolutions;
    //cached manager
    public WorkingSA workingSA;

    //generate a new random solution.
    public static SASolution generateRandomSolution(WorkingSA _workingSA, int solutionLength, double bottomBound, double upperBound) {
        double[] sol = new double[solutionLength];
        Random r = new Random();
        double totalValue = upperBound - bottomBound;
        for (int i = 0 ; i < solutionLength; i ++) {
            sol[i] = bottomBound + r.nextDouble() * totalValue ;
        }
        return new SASolution(_workingSA, sol);
    }

    public SASolution (WorkingSA _workingSA, double[] _variables) {
        workingSA = _workingSA;
        mySolutions = _variables;
    }

    public SASolution generateNeighborSolutions(double limit) {
        Random r = new Random();
        double[] returnDouble = new double[mySolutions.length];
        for (int i = 0; i < mySolutions.length; i ++) {
            returnDouble[i] = mySolutions[i] - limit / 2 + r.nextDouble() * limit;
        }
        return new SASolution(workingSA, returnDouble);
    }

    public double getFitness() {
        return workingSA.solveEquation(mySolutions);
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < mySolutions.length; i ++) {
            returnString += mySolutions[i] + " , ";
        }
        returnString += "f(x) = " + getFitness();
        return returnString;
    }
}
