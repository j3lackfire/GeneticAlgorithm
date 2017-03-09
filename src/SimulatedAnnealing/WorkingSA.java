package SimulatedAnnealing;

import Equations.EquationVariable;
import Equations.MiniEquation;
import Equations.MultipleVariablesEquation;

import javax.swing.*;
import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 3/9/2017.
 */
//working simulated annealing
public class WorkingSA {

    private double big_T; //The big T
    private int small_t; //small t

    private int numberOfIteration = 30; // small n

    private double neighborhoodLength;

    private double terminatePoint = 0.0001;

    private double neighborLimit = 0.1;
    private double bottomBound = -10;
    private double upperBound = 10;

    public SASolution solution;

    public WorkingSA() {
        Random r  = new Random();
        TemperatureScheduler tScheduler = new TemperatureScheduler();
        big_T = tScheduler.currentTemp;
        double solutionRange = upperBound - bottomBound;
        small_t = 0;
        SASolution mySolution = SASolution.generateRandomSolution(this, 2, bottomBound, upperBound);
        boolean shouldEscape = false;
        while (!shouldEscape) {
            SASolution tempSolution = mySolution.generateNeighborSolutions(tScheduler.getCurrentNeighborhoodLimit() * solutionRange);
            double deltaE = tempSolution.getFitness() - mySolution.getFitness();
            boolean shouldContinue = false;

            if (deltaE < 0) {
                shouldContinue = true;
            } else {
                if (r.nextDouble() <= Math.exp(-deltaE / big_T)) {
                    shouldContinue = true;
                }
            }
            if (shouldContinue) {
                mySolution = tempSolution;
                small_t ++;
                //i am not very sure about this
                if (Math.abs(deltaE) <= terminatePoint) {
//                    System.out.println("A solution is found !!!");
//                    System.out.println(mySolution.toString());
                    shouldEscape = true;
                    break;
                }
                if (small_t % numberOfIteration == 0) {
                    if (!tScheduler.shouldExit()) {
                        big_T = tScheduler.getNextTemp();
                    } else {
                        shouldEscape = true;
                    }
                }
            }

        }
        solution = mySolution;
    }

    public SASolution getSolution() {return solution;}

    private SASolution findBestSolution (SASolution[] _solution) {
        int bestSolutionIndex = 0;
        double bestValue = 99999;
        for (int i = 0; i < _solution.length; i ++) {
            if (_solution[i].getFitness() < bestValue) {
                bestSolutionIndex = i;
                bestValue = _solution[i].getFitness();
            }
        }
        return _solution[bestSolutionIndex];
    }

    //hard code the solution inside
    public double solveEquation(double[] values) {
        //y = 3*x1^2 - 2*x1*x2 + 3*x2^2 - x1 - x2
        MultipleVariablesEquation f_x = new MultipleVariablesEquation();
        f_x.addMiniEquation(new MiniEquation(3, new EquationVariable(1,2)));
        f_x.addMiniEquation(new MiniEquation(-2,
                new EquationVariable[]{new EquationVariable(1,1), new EquationVariable(2,1)}));
        f_x.addMiniEquation(new MiniEquation(3, new EquationVariable(2,2)));
        f_x.addMiniEquation(new MiniEquation(-1, new EquationVariable(1,1)));
        f_x.addMiniEquation(new MiniEquation(-1, new EquationVariable(2,1)));
        return f_x.calculate(values);
    }
}
