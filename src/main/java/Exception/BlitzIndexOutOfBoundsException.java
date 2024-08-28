package exception;

public class BlitzIndexOutOfBoundsException extends BlitzException {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates nonexistent task number.
     */
    @Override
    public String toString() {
        return "There is no such task in the list!";
    }
}
