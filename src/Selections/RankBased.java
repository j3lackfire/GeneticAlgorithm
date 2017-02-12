package Selections;

import GASolutions.GASolution;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;

/**
 * Created by Le Pham Minh Duc on 2/12/2017.
 */
public class RankBased extends Selections{
    private static double[] rankPercent = new double[]{0.4, 0.3, 0.6};
    public static ArrayList<Integer> slotDistribution = new ArrayList<>();
    //first ranks takes 40% of the place
    //second ranks takes 30% of all the place
    //every runner up is 50% less than the previous, so 3rd place is 15, 4th is 7.5 ....

    public static GASolution[] generateMatingPool(GASolution[] _population) {
        GASolution matingPool[] = new GASolution[_population.length];
        ArrayList<GASolution> returnList = new ArrayList<>();
        int rankNeeded = getNumberOfRankNeeded(_population.length);
        GASolution[] bestSolutions = getAndSortSolution(_population, rankNeeded);
        for (int i = 0; i < slotDistribution.size(); i ++) {
            for (int j = 0; j < slotDistribution.get(i); j ++) {
                returnList.add(bestSolutions[i].copySolution());
            }
        }
        Collections.shuffle(returnList);
        matingPool = returnList.toArray(matingPool);
        return matingPool;
    }

    public static GASolution[] getAndSortSolution(GASolution[] _population, int _length) {
        ArrayList<GASolution> returnList =  new ArrayList<>();
        for (int i = 0; i < _population.length; i ++) {
            if (returnList.size() < _length) {
                returnList.add(_population[i]);
            } else {
                double currentFitness = _population[i].getFitness();
                boolean shouldAdd = false;
                for (int j = 0; j < returnList.size(); j ++) {
                    if (currentFitness > returnList.get(j).getFitness()) {
                        shouldAdd = true;
                        break;
                    }
                }
                //find the worst solution and remove it
                if (shouldAdd) {
                    double worstFitness = 2;
                    int worstFitnessIndex = -1;
                    for (int j = 0; j < returnList.size(); j ++) {
                        if (returnList.get(j).getFitness() < worstFitness) {
                            worstFitness = returnList.get(j).getFitness();
                            worstFitnessIndex = j;
                        }
                    }
                    returnList.remove(worstFitnessIndex);
                    returnList.add(_population[i]);
                }
            }
        }
        Collections.sort(returnList);
        GASolution[] returnSolution = new GASolution[_length];
        returnSolution = returnList.toArray(returnSolution);
        return returnSolution;
    }



    //for example, with 20, we have 8, 6, 3, 1, ,1, 1 for each rank so the number is 6
    public static int getNumberOfRankNeeded(int populationLength) {
        slotDistribution = new ArrayList<Integer>();
        int availableSlot = populationLength;
        for (int i = 0; i < rankPercent.length - 1; i ++) {
            availableSlot -= (int)(populationLength * rankPercent[i]);
            slotDistribution.add((int)(populationLength * rankPercent[i]));
            if (availableSlot <= 0) {
                return slotDistribution.size();
            }
        }

        double lastPercent = rankPercent[rankPercent.length - 1];
        while (availableSlot > 0) {
            int removeThisTurn = (int)(availableSlot * lastPercent);
            if (availableSlot - removeThisTurn <= 0) {
                slotDistribution.add(availableSlot);
                availableSlot = 0;
            } else {
                if (availableSlot == 1) {
                    slotDistribution.add(1);
                    availableSlot = 0;
                } else {
                    availableSlot -= removeThisTurn;
                    slotDistribution.add(removeThisTurn);
                }
            }
        }
        return slotDistribution.size();
    }

}
