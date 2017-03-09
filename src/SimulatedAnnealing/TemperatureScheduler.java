package SimulatedAnnealing;

/**
 * Created by Le Pham Minh Duc on 3/9/2017.
 */
public class TemperatureScheduler {
    double initialTemp = 100;
    double finalTemp = 5;
    double reduceStep = 2.5;
    double currentTemp;

    public TemperatureScheduler() {
        currentTemp = initialTemp;
    }

    public boolean shouldExit() {
        return currentTemp <= finalTemp;
    }

    public double getNextTemp() {
        currentTemp -= reduceStep;
        return currentTemp;
    }

    public double getCurrentNeighborhoodLimit() {
        return (currentTemp / initialTemp);
    }
}
