package GASolutions;

import BinaryFatory.BinaryVariable;

/**
 * Created by Le Pham Minh Duc on 2/11/2017.
 */
public class GASolution implements Comparable<GASolution>{
    public BinaryVariable[] mySolutions;
    //cached manager
    private WorkingGA workingGA;
    //C
    private double cumulativeProbabilityOfSelection;
    public int matingPoolCount = 0;

    public GASolution(WorkingGA _workingGA, BinaryVariable[] _variables) {
        workingGA = _workingGA;
        mySolutions = _variables;
    }

    //Get f(x)
    public double getValue() {
        double[] variablesValue = new double[mySolutions.length];
        for (int i = 0; i < mySolutions.length; i ++) {
            variablesValue[i] = mySolutions[i].getValue();
        }
        return workingGA.solveEquation(variablesValue);
    }

    //get F(x)
    public double getFitness() {return 1 / (1 + getValue());}

    //Get Expected Count A = F(x) / F_average(x)
    public double getExpectedCount() { return getFitness() / workingGA.getAverageFitness(); }

    //Get Probability of selection B = A / populationCount
    public double getProbabilityOfSelection() { return getExpectedCount() / workingGA.getPopulationCount(); }

    //C = population chance + this B
    public double setCumulativeProbabilityOfSelection(double currentProbability) { return cumulativeProbabilityOfSelection = currentProbability + getProbabilityOfSelection(); }

    public double getCumulativeProbabilityOfSelection() {return cumulativeProbabilityOfSelection;}

    //return a new object. Doing this to fix some weird bug with java
    public GASolution copySolution() {
        BinaryVariable[] paraVariables = new BinaryVariable[mySolutions.length];
        for (int i = 0; i < mySolutions.length; i ++) {
            paraVariables[i] = mySolutions[i].copyBinary();
        }
        GASolution returnSolution = new GASolution(workingGA, paraVariables);
        returnSolution.cumulativeProbabilityOfSelection = cumulativeProbabilityOfSelection;
        return returnSolution;
    }

    public String toStringFull() {
        String returnString = "";
        for (int i = 0 ; i < mySolutions.length; i ++) {
            returnString += mySolutions[i].toString() + " - ";
        }
        returnString += "\nB: " + getProbabilityOfSelection() + " \\ C: " + cumulativeProbabilityOfSelection
                + " \\ F: " + matingPoolCount + ".....\n";
        return  returnString;
    }

    public String toStringDebug() {
        String returnString = "";
        for (int i = 0; i < mySolutions.length; i++) {
            returnString += mySolutions[i].toString() + " , ";
        }
        returnString += " B: " + getProbabilityOfSelection();
        return returnString;

    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < mySolutions.length; i++) {
            returnString += mySolutions[i].getValue() + " , ";
        }
        returnString += "f(x): " + getValue() + " - F(x) " + getFitness();
//        returnString += "C " + cumulativeProbabilityOfSelection;

        return returnString;
    }

    @Override
    public int compareTo(GASolution other) {
        //write code here for compare name
        return getFitness() > other.getFitness() ? -1 : 1;
    }


}
