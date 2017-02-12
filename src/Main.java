import Selections.RankBased;
import Selections.SelectionMethod;
import GASolutions.WorkingGA;

public class Main {

    public static void main(String[] args) {
        System.out.println("\n\n\n\n\n\n");
        int loopCount = 500;
//        WorkingGA myWorkingGA = new WorkingGA(20, 1000, SelectionMethod.RankBased);
//        System.out.println(myWorkingGA.getSolution() + " - " + myWorkingGA.getTotalGeneration());

        benchGA(loopCount, SelectionMethod.RouletteWheel);
        benchGA(loopCount, SelectionMethod.Tournament);
        benchGA(loopCount, SelectionMethod.RankBased);
    }

    public static void benchGA(int loopCount, SelectionMethod method) {
        long startTime = System.currentTimeMillis();
        long averageGen = 0;
        for (int i = 0; i < loopCount; i ++) {
            WorkingGA myWorkingGA = new WorkingGA(20, 1000, method);
            averageGen += myWorkingGA.getTotalGeneration();
        }
        long endTime = System.currentTimeMillis();
        averageGen /= (long)loopCount;
        System.out.println(method.toString() + " - Average loop count " + averageGen + " - execution time " + (endTime - startTime));
    }
}

