package exception;

public class BlitzNumberFormatException extends BlitzException {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates integer parameter is required.
     */
    @Override
    public String toString() {
        return "Integer value is expected for parameter!";
    }
}
