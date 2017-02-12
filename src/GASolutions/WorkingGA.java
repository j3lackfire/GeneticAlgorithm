package GASolutions;

import BinaryFatory.BinaryFactory;
import BinaryFatory.BinaryVariable;
import Equations.EquationVariable;
import Equations.MiniEquation;
import Equations.MultipleVariablesEquation;
import Selections.RankBased;
import Selections.RouletteWheel;
import Selections.SelectionMethod;
import Selections.Tournament;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 2/11/2017.
 */
public class WorkingGA {

    private double crossoverChance = 0.8;
    private double mutationChance = 0.05;
    private int generationCap; //maximum number of generation
    private double acceptableFitness = 0.999;
    public int populationCount = 20;

    public int binaryLength = 10;


    //private variables, for caching
    private BinaryFactory binaryFactory;
    private double averageFitness;
    private Random random = new Random();
    private GASolution globalSolution;
    public long totalGeneration = 0;

    public WorkingGA(int _populationCount, int _generationCap, SelectionMethod _selectionMethod) {
        populationCount = _populationCount;
        generationCap = _generationCap;
        binaryFactory = new BinaryFactory(binaryLength, 0, 6);

        //a new solution is created with all the values settle
        GASolution[] initialSolution = generateNewRandomSolution();
        //set the global solution to be the best solution in this generation
        //use copy function to make sure it's return a NEW object, not a reference of the solution,
        //which cause a lot of bug later on :/
        globalSolution = getBestSolution(initialSolution).copySolution();

        //if this is not the global optimum (of course it shouldn't be)
        if (globalSolution.getFitness() < acceptableFitness) {
            //create the first iteration from the initial generation
            GASolution[] nextGeneration = generateMatingPool(initialSolution, _selectionMethod);
            for (int i = 0; i < generationCap; i ++) {
                //do cross over
                nextGeneration = doCrossover(nextGeneration);
                //do mutation
                nextGeneration = doMutation(nextGeneration);
                //by now, we have a new set of solution, let's find its best
                GASolution localSolution = getBestSolution(nextGeneration);
                if (globalSolution.getFitness() < localSolution.getFitness()) {
                    globalSolution = localSolution.copySolution();
                }
                if (globalSolution.getFitness() > acceptableFitness) {
                    totalGeneration = (long)i;
                    break;
                }
                //if the solution isn't the optimal solution, create the mating pool again
                prepareSolution(nextGeneration);
                nextGeneration = generateMatingPool(nextGeneration, SelectionMethod.RouletteWheel);
            }
            totalGeneration ++;
        }
        if (totalGeneration == generationCap - 1) {
            System.out.println("Generation cap reached" + globalSolution.getFitness());
        }
    }

    public GASolution getSolution() { return globalSolution; }

    public long getTotalGeneration() { return totalGeneration;}

    private GASolution getBestSolution(GASolution[] _solution) {
        int chosenIndex = -1;
        double bestFitness = -1;
        for (int i = 0; i < _solution.length; i++) {
            if (_solution[i].getFitness() > bestFitness) {
                bestFitness = _solution[i].getFitness();
                chosenIndex = i;
            }
        }
        return _solution[chosenIndex];
    }

    private GASolution[] generateNewRandomSolution() {
        //Create the generation
        GASolution mySolutions[] = new GASolution[populationCount];
        for (int i = 0; i < populationCount; i ++) {
            mySolutions[i] = new GASolution(this, new BinaryVariable[] {binaryFactory.generateNewBinary(), binaryFactory.generateNewBinary()});
        }
        //Prepare solution, in which we calculate the average f(x), get the cumulative probability of selection
        prepareSolution(mySolutions);
        return mySolutions;
    }

    private void prepareSolution(GASolution[] _solution) {
        averageFitness = getPopulationAverageFitness(_solution);
        double currentC = 0;
        for (int i = 0; i < populationCount; i ++) {
            _solution[i].matingPoolCount = 0;
            currentC = _solution[i].setCumulativeProbabilityOfSelection(currentC);
        }
    }

    private GASolution[] generateMatingPool(GASolution[] population, SelectionMethod selectionMethod) {
        switch (selectionMethod) {
            case RouletteWheel:
                return RouletteWheel.generateMatingPool(population);
            case Tournament:
                return Tournament.generateMatingPool(population);
            case RankBased:
                return RankBased.generateMatingPool(population);
            default:
                return null;
        }
    }

    private GASolution[] doCrossover(GASolution[] population) {
        GASolution[] returnSolutions = new GASolution[population.length];
        for (int i = 0; i < population.length / 2; i ++) {
            if (random.nextDouble() < crossoverChance) {
                int crossoverPoint = random.nextInt(binaryLength * 2);
                if (crossoverPoint < binaryLength) {
                    population[2 * i].mySolutions[0].doSinglePointCrossoverWith(population[2 * i+1].mySolutions[0], crossoverPoint);
                    population[2 * i].mySolutions[1].doSinglePointCrossoverWith(population[2 * i+1].mySolutions[1], 0);
                } else {
                    population[2 * i].mySolutions[1].doSinglePointCrossoverWith(population[2 * i+1].mySolutions[1], crossoverPoint - binaryLength);
                }
            }
            returnSolutions[2 * i]= population[2 * i].copySolution();
            returnSolutions[2 * i + 1]= population[2 * i + 1].copySolution();
        }
        return returnSolutions;
    }


    private GASolution[] doMutation(GASolution[] population) {
        GASolution[] returnSolutions = new GASolution[population.length];
        for (int i = 0; i < population.length; i ++) {
            returnSolutions[i] = population[i].copySolution();
            for (int j = 0; j < population[i].mySolutions.length; j ++) {
                returnSolutions[i].mySolutions[j] = population[i].mySolutions[j].doBitWiseMutation(mutationChance);
            }
        }
        return returnSolutions;
    }

    private String printPopulation(GASolution[] population) {
        String returnString = "";
        for (int i = 0; i < population.length; i ++) {
            returnString += population[i].toStringDebug() + "\n";
        }
        System.out.println(returnString);
        return returnString;
    }

    private double getPopulationAverageFitness(GASolution[] _solutions) {
        double fitness = 0;
        for (int i = 0; i < _solutions.length; i ++) {
            fitness += _solutions[i].getFitness();
        }
        return fitness / _solutions.length;
    }

    public double getAverageFitness() {return averageFitness;}

    public int getPopulationCount() {return populationCount;}

    //solve: f(x1, x2) = (x_1^2 + x_2 - 11)^2 + (x_1 + x_2^2 - 7)^2
    public double solveEquation(double[] values) {
        //y = f(x1, x2) = 4*x1^2 + 3*x2^2 - 6*x1*x2 - 4&x1, -10.0 <= x1, x2 <= 10.0

        //f_1 = (x_1^2 + x_2 - 11)
        MultipleVariablesEquation f_1 = new MultipleVariablesEquation();
        f_1.addMiniEquation(new MiniEquation(1, new EquationVariable(1,2)));
        f_1.addMiniEquation(new MiniEquation(1, new EquationVariable(2)));
        f_1.addMiniEquation(new MiniEquation(-11, new EquationVariable(1,0)));

        //f_2 = (x_1 + x_2^2 - 7)
        MultipleVariablesEquation f_2 = new MultipleVariablesEquation();
        f_2.addMiniEquation(new MiniEquation(1, new EquationVariable(1)));
        f_2.addMiniEquation(new MiniEquation(1, new EquationVariable(2,2)));
        f_2.addMiniEquation(new MiniEquation(-7, new EquationVariable(1,0)));

        double f_1_result = f_1.calculate(values);
        double f_2_result = f_2.calculate(values);

        double finalResult = f_1_result * f_1_result + f_2_result * f_2_result;
        return finalResult;
    }
}
