package ParticleSwarm;

import Equations.EquationVariable;
import Equations.MiniEquation;
import Equations.MultipleVariablesEquation;

/**
 * Created by Le Pham Minh Duc on 3/9/2017.
 */
public class WorkingSwarm {

    public double c_1  = 0.8;
    public double c_2 = 0.2;

    private double bottomBound = -10;
    private double upperBound = 10;

    private int swarmSize = 60;
    private SwarmParticle[] swarms;
    public double[] globalSolution;
    public double globalFitness;

    public int maximumLoop = 50;

    public WorkingSwarm() {
        swarms = new SwarmParticle[swarmSize];
        for (int i = 0; i < swarmSize; i ++) {
            swarms[i] = new SwarmParticle(this, 2, bottomBound, upperBound);
        }
        globalSolution = new double[2];
        copySolutionToGlobalSolution(findLocalBest(swarms));
        int numberOfLoop = 0;
        while (numberOfLoop < maximumLoop) {
            for (int i = 0; i < swarmSize; i ++) {
                swarms[i].swarmOptimization(globalSolution);
            }
            if (findLocalBest(swarms).getFitness() < globalFitness) {
                copySolutionToGlobalSolution(findLocalBest(swarms));
            }
            numberOfLoop ++;
        }
        System.out.println("Final solution " + globalSolution[0] + " , " + globalSolution[1] + " , f(x) = " + globalFitness);
    }

    private SwarmParticle findLocalBest(SwarmParticle[] _solutions) {
        int returnIndex = 0;
        double bestFitness = 9999999;
        for (int i = 0; i < _solutions.length; i ++) {
            if (_solutions[i].getFitness() < bestFitness) {
                bestFitness = _solutions[i].getFitness();
                returnIndex = i;
            }
        }
        return _solutions[returnIndex];
    }

    private void copySolutionToGlobalSolution(SwarmParticle _particle) {
        for (int i = 0; i < _particle.solutions.length; i ++) {
            globalSolution[i] = _particle.solutions[i];
        }
        globalFitness = _particle.getFitness();
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
