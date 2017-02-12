package BinaryFatory;

import java.util.Random;

/**
 * Created by Le Pham Minh Duc on 2/11/2017.
 */
public class BinaryVariable {
    public int binaryLength; //this case, this is 10
    public double bottomBound; //0
    public double upperBound; //6
    private boolean[] binaryString;

    public BinaryVariable(BinaryVariable source) {
        binaryLength = source.binaryLength;
        bottomBound = source.bottomBound;
        upperBound = source.upperBound;
        binaryString = new boolean[binaryLength];
        for (int i = 0; i < binaryLength; i ++) {
            binaryString[i] = source.binaryString[i];
        }
    }

    //create binary variable from a string
    public BinaryVariable(int _binaryLength, double _bottomBound, double _upperBound, String _binaryString) {
        binaryLength = _binaryLength;
        bottomBound = _bottomBound;
        upperBound = _upperBound;
        binaryString = new boolean[binaryLength];
        char[] binaryChars = _binaryString.toCharArray();
        for (int i = 0; i < binaryLength; i ++ ) {
            binaryString[i] = binaryChars[i] == '1';
        }
    }

    //Create new binary variable
    public BinaryVariable(int _binaryLength, double _bottomBound, double _upperBound) {
        binaryLength = _binaryLength;
        bottomBound = _bottomBound;
        upperBound = _upperBound;
        binaryString = new boolean[binaryLength];
        Random random = new Random();
        for (int i = 0; i < binaryLength; i ++) {
            binaryString[i] = random.nextBoolean();
        }
    }

    public BinaryVariable copyBinary() {
        return new BinaryVariable(this);
    }

    public void doSinglePointCrossoverWith(BinaryVariable other, int point) {
        for(int i = point; i < binaryLength; i ++) {
            if (binaryString[i] != other.binaryString[i]) {
                binaryString[i] = !binaryString[i];
                other.binaryString[i] = !other.binaryString[i];
            }
        }
    }

    public BinaryVariable doBitWiseMutation(double mutationChance) {
        Random random = new Random();
        for (int i = 0; i < binaryLength; i ++) {
            if (random.nextDouble() < mutationChance) {
                binaryString[i] = !binaryString[i];
            }
        }
        BinaryVariable returnBinary = new BinaryVariable(this);
        return returnBinary;
    }

    public String toString() {
        String returnString = "";
        for (int i = 0; i < binaryLength ; i ++) {
            returnString += binaryString[i] ? "1" : "0";
        }
        returnString += " - " + toDecimal();
        return returnString;
    }

    public int toDecimal() {
        int returnNumber = 0;
        for (int i = 0; i < binaryLength; i ++) {
            returnNumber += binaryString[binaryLength - i - 1] ? Math.pow(2, i) : 0;
        }
        return returnNumber;
    }

    public double getValue() {
        return bottomBound + (upperBound - bottomBound) * (toDecimal() / (Math.pow(2, binaryLength) - 1));
    }
}
