package BinaryFatory;

/**
 * Created by Le Pham Minh Duc on 2/11/2017.
 */
public class BinaryFactory {
    public int binaryLength;
    public double bottomBound;
    public double upperBound;

    public BinaryFactory(int _binaryLength, double _bottomBound, double _upperBound) {
        binaryLength = _binaryLength;
        bottomBound = _bottomBound;
        upperBound = _upperBound;
    }

    public BinaryVariable generateNewBinary() {
        return new BinaryVariable(binaryLength, bottomBound, upperBound);
    }

    public BinaryVariable stringToBinary(String _binaryString) {
        return new BinaryVariable(binaryLength, bottomBound, upperBound, _binaryString);
    }
}
