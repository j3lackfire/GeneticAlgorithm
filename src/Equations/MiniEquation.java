package Equations;

import Equations.EquationVariable;

import java.util.ArrayList;

/**
 * Created by Le Pham Minh Duc on 1/30/2017.
 * Mini equation is use as part of a multi variables equation.
 */
public class MiniEquation {
    public ArrayList<EquationVariable> variables = new ArrayList<>();
    public double multiple;

    public MiniEquation(double _multipleNumber, EquationVariable _variable) {
        addMultipleNumber(_multipleNumber);
        addVariable(_variable.id, _variable.power);
    }

    public MiniEquation(double _multipleNumber, EquationVariable[] _variables) {
        addMultipleNumber(_multipleNumber);
        for (int i = 0; i < _variables.length; i ++) {
            addVariable(_variables[i].id, _variables[i].power);
        }
    }

    public void addMultipleNumber(double _multiple) {
        multiple = _multiple;
    }

    public void addVariable(int xID, double _power) {
        variables.add(new EquationVariable(xID, _power));
    }

    public double calculate(double[] valueList) {
        double returnValue = multiple;
        int variablesIndex = 0;
        for (int i = 0; i < valueList.length; i ++) {
            if (variables.get(variablesIndex).id - 1 == i) { //x1 match to valueList[0], x2 to valueList[1] and so on ....
                returnValue *= variables.get(variablesIndex).calculate(valueList[i]);
                variablesIndex ++;
                if (variablesIndex > variables.size() - 1) {
                    break;
                }
            }
        }
        return returnValue;
    }

    public String toString() {
        String returnString;
        if (multiple < 0) {
            returnString = " ";
        } else {
            returnString = " +";
        }
        if (multiple != 1.0) {
            returnString += multiple + " * ";
        }

        for (int i = 0; i < variables.size() - 1; i++) {
            returnString += variables.get(i).toString() + " * ";
        }
        returnString += variables.get(variables.size() - 1).toString();
        return returnString;
    }

}
