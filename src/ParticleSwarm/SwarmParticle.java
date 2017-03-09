package ParticleSwarm;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 3/9/2017.
 */
public class SwarmParticle {
    private WorkingSwarm workingSwarm;
    public double[] solutions;
    public double[] velocity;
    public double[] personalBest;
    private double personalBestFitness;

    public SwarmParticle(WorkingSwarm _workingSwarm, int solutionLength, double bottomBound, double upperBound) {
        workingSwarm = _workingSwarm;
        solutions = new double[solutionLength];
        velocity = new double[solutionLength];
        personalBest = new double[solutionLength];

        Random r = new Random();
        double totalValue = upperBound - bottomBound;
        for (int i = 0 ; i < solutionLength; i ++) {
            solutions[i] = bottomBound + r.nextDouble() * totalValue ;
            velocity[i] = -1 * r.nextDouble() * 2; //random from -1 to 1
        }
        savePersonalBest();
    }

    public void swarmOptimization(double[] globalSolution) {
        for (int i = 0; i < solutions.length; i ++) {
            velocity[i] = velocity[i]
                    + workingSwarm.c_1 * (personalBest[i] - solutions[i])
                    + workingSwarm.c_2 * (globalSolution[i] - solutions[i]);

            solutions[i] += velocity[i];
        }

        checkAndUpdatePersonalBest();
    }

    public void checkAndUpdatePersonalBest() {
        if (isPersonalBest()) {
            savePersonalBest();
        }
    }

    private boolean isPersonalBest() {
        return (getFitness() < personalBestFitness);
    }

    public SwarmParticle(WorkingSwarm _workingSwarm, double[] _solutions, double[] _velocity, double[] _personalBest) {
        workingSwarm = _workingSwarm;
        for (int i = 0; i < _solutions.length; i ++) {
            solutions[i] = _solutions[i];
            velocity[i] = _velocity[i];
            personalBest[i] = _personalBest[i];
        }
    }

    public SwarmParticle clone() {
        return new SwarmParticle(workingSwarm, solutions, velocity, personalBest);
    }

    public double getFitness() {
        return workingSwarm.solveEquation(solutions);
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < solutions.length; i ++) {
            returnString += solutions[i] + " , ";
        }
        returnString += "f(x) = " + getFitness();
        return returnString;
    }

    public void savePersonalBest() {
        for (int i = 0; i < solutions.length; i ++) {
            personalBest[i] = solutions[i];
        }
        personalBestFitness = getFitness();
    }
}
