package Equations;

import java.util.ArrayList;

/**
 * Created by Le Pham Minh Duc on 1/29/2017.
 */
public class MultipleVariablesEquation {
    public ArrayList<MiniEquation> myEquations = new ArrayList<>();

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
