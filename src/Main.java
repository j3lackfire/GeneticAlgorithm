import Selections.RankBased;
import Selections.SelectionMethod;
import GASolutions.WorkingGA;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n\n");
//        WorkingGA myWorkingGA = new WorkingGA(20, 1000, SelectionMethod.RankBased);
//        System.out.println(myWorkingGA.getSolution() + " - " + myWorkingGA.getTotalGeneration());
//        System.out.println("---------------");
//        System.out.println(myWorkingGA.solveEquation(new double[]{2,3}));
//        System.out.println(myWorkingGA.solveEquation(new double[]{3,2}));
        int loopCount = 500;
        benchGA(loopCount, SelectionMethod.RouletteWheel);
        benchGA(loopCount, SelectionMethod.Tournament);
        benchGA(loopCount, SelectionMethod.RankBased);
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

