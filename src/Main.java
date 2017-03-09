import Equations.MultipleVariablesEquation;
import ParticleSwarm.WorkingSwarm;
import Selections.RankBased;
import Selections.SelectionMethod;
import GASolutions.WorkingGA;
import SimulatedAnnealing.SASolution;
import SimulatedAnnealing.WorkingSA;

public class Main {

    public static void main(String[] args) {
        WorkingSwarm myWorkingSwarm = new WorkingSwarm();

//        WorkingGA myWorkingGA = new WorkingGA(40, 1000, SelectionMethod.RankBased);
//        System.out.println(myWorkingGA.getSolution());

//        int loopCount = 100;
//        benchGA(loopCount, SelectionMethod.RouletteWheel);
//        benchGA(loopCount, SelectionMethod.Tournament);
//        benchGA(loopCount, SelectionMethod.RankBased);

//        benchSA(50);

    }

    public static void benchSA(int loopCount) {
        long startTime = System.currentTimeMillis();
        SASolution finalSolution = null;
        double lowestFitness = 9999;
        for (int i = 0; i < loopCount; i ++) {
            WorkingSA myWorkingSA = new WorkingSA();
            SASolution mySolution = myWorkingSA.getSolution();
            if (mySolution.getFitness() < lowestFitness) {
                lowestFitness = mySolution.getFitness();
                finalSolution = mySolution;
            }
        }
        System.out.println("Final solution " + finalSolution.toString() + " - execution time " + (System.currentTimeMillis() - startTime));

    }

    public static void benchGA(int loopCount, SelectionMethod method) {
        long startTime = System.currentTimeMillis();
        long averageGen = 0;
        for (int i = 0; i < loopCount; i ++) {
            WorkingGA myWorkingGA = new WorkingGA(40, 1000, method);
            averageGen += myWorkingGA.getTotalGeneration();
        }
        long endTime = System.currentTimeMillis();
        averageGen /= (long)loopCount;
        System.out.println(method.toString() + " - Average loop count " + averageGen + " - execution time " + (endTime - startTime));
    }
}

