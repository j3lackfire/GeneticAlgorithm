package Selections;

import GASolutions.GASolution;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 2/12/2017.
 */
public class MiniTournament {
    GASolution[] mySolutions;

    public MiniTournament(GASolution[] _population, double percentPicked) {
        Random random = new Random();
        int tournamentSize = (int) (_population.length * percentPicked);
        mySolutions = new GASolution[tournamentSize];
        ArrayList<Integer> possibleSolution = new ArrayList<>();
        for (int i = 0; i < _population.length; i++) {
            possibleSolution.add(i);
        }
        for (int i = 0; i < mySolutions.length; i++) {
            int indexPicked = random.nextInt(possibleSolution.size());
            mySolutions[i] = _population[possibleSolution.get(indexPicked)];
            possibleSolution.remove(indexPicked);
        }
    }

    public GASolution getWinner() {
        int index = -1;
        double bestFitness = -1;
        for (int i = 0; i < mySolutions.length; i++) {
            if (bestFitness < mySolutions[i].getFitness()) {
                bestFitness = mySolutions[i].getFitness();
                index = i;
            }
        }
        return mySolutions[index].copySolution();
    }
}
