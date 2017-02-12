package Selections;

import GASolutions.GASolution;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 2/12/2017.
 */
public class RouletteWheel extends Selections{

    public static GASolution[] generateMatingPool(GASolution[] population) {
        Random random = new Random();
        GASolution matingPool[] = new GASolution[population.length];
        for (int i = 0; i < population.length; i ++) {
            //generate D
            double dValue = random.nextDouble();
            //get match for it.
            for (int j = 0; j < population.length; j ++) {
                if (dValue < population[j].getCumulativeProbabilityOfSelection()) {
                    population[j].matingPoolCount++;
                    matingPool[i] = population[j].copySolution();
                    break;
                }
            }
            if (matingPool[i] == null) {
                System.out.println("UNEXPECTED BUG !!! " + i + " , " + dValue + " ," + population[population.length - 1].getCumulativeProbabilityOfSelection());
            }
        }
        return matingPool;
    }

}
