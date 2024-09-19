package devon;


/**
 * Represents a base exception for the Devon application.
 * This is an abstract class that serves as a common parent for all exceptions related to Devon.
 */
public abstract class DevonException extends Exception {

    /**
     * Returns a default string representation of the exception.
     *
     * @return A string indicating an error occurred: "OOPS!!!".
     */
    @Override
    public String toString() {
        return "OOPS!!!";
    }
}
