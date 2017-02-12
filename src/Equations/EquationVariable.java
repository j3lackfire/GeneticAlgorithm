package Equations;

/**
 * Created by Le Pham Minh Duc on 1/29/2017.
 */
public class EquationVariable {
    public int id;
    public double multiple;
    public double power;

    public EquationVariable(int _id) {
        id = _id;
        multiple = 1;
        power = 1;
    }

    public EquationVariable(int _id, double _power) {
        id = _id;
        multiple = 1;
        power = _power;
    }

    public EquationVariable(int _id, double _multiple, double _power) {
        id = _id;
        multiple = _multiple;
        power = _power;
    }

    public double calculate(double x){
        return multiple * (Math.pow(x,power));
    }

    public String toString() {
        if (multiple == 0) {
            return "0";
        }
        if (power == 0) {
            return " " + multiple;
        }
        String returnString = "";
        if (multiple != 1) {
            returnString += multiple + " * ";
        }
        returnString += "x";
        if (id != 0) {
            returnString += "_" + id;
        }
        if (power != 1) {
            returnString += "^" + power;
        }
        return returnString;
    }
}
