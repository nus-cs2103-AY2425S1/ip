package genji;

/**
 * A Exception class that handle errors related to the program
 */
public class GenjiException extends Exception {

    /**
     * Constructor of GenjiException
     * @param s the specific error message
     */
    public GenjiException(String s) {
        super(s);
    }
}
