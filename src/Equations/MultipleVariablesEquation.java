package Equations;

import java.util.ArrayList;

/**
 * Created by Le Pham Minh Duc on 1/29/2017.
 */
public class MultipleVariablesEquation {
    public ArrayList<MiniEquation> myEquations = new ArrayList<>();

    //trying to do create a function from string, but I don't have enough time to implement that :(
//    public MultipleVariablesEquation(String equation) {
//        //If exist "(" and ")" that's mean that's a big variables equation, that has multiplication
//        //For example: This is a big equation,
//        //f(x1, x2) = 4*x1^2 + 3*x2^2 - 6*x1*x2 - 4&x1, -10.0 <= x1, x2 <= 10.0
//        //y = 3*x1^2 - 2*x1*x2 + 3*x2^2 - x1 - x2 -> is a small equation
//        String cachedString;
//        if (equation.contains("(")) {
//
//        } else {
//            //remove all space
//            char[] charString = equation.replaceAll(" ", "").toCharArray();
//            ArrayList<Character> cachedChar = new ArrayList<>();
//            for (int i = 0; i < charString.length; i ++) {
//                if (charString[i] == '+' || charString[i] == '-') {
//
//                }
//            }
//
//        }
//    }

    public MultipleVariablesEquation() {}

    public void addMiniEquation(MiniEquation miniEquation) {
        myEquations.add(miniEquation);
    }

    public double calculate(double[] values) {
        double returnValue = 0;
        String s = "";
        for (int i = 0; i < myEquations.size(); i ++) {
            returnValue += myEquations.get(i).calculate(values);
            s += myEquations.get(i).calculate(values) + " ";
        }
//        System.out.println(s);
        return returnValue;
    }

    public String toString() {
        String returnString = "";
        for(int i = 0; i < myEquations.size(); i ++) {
            returnString += myEquations.get(i).toString();
        }
        return  returnString;
    }
}
