package exception;

public class BlitzException extends Exception {
    /**
     * Returns a String representation of this object.
     *
     * @return String indicates something is wrong.
     */
    @Override
    public String toString() {
        return "Something is wrong, please try again!";
    }
}
