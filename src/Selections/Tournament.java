package Selections;

import GASolutions.GASolution;

import java.util.ArrayList;
import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 2/12/2017.
 */
public class Tournament extends Selections {
    private static double percentOfTournamentPicked = 0.2; //20% of of 20 is 4

    public static GASolution[]generateMatingPool(GASolution[] _population) {
        GASolution matingPool[] = new GASolution[_population.length];
        for (int i = 0; i < _population.length; i ++) {
            MiniTournament miniTournament= new MiniTournament(_population, percentOfTournamentPicked);
            matingPool[i] = miniTournament.getWinner();
        }
        return matingPool;
    }
}
