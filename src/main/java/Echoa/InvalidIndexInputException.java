package Echoa;

/**
 * InvalidIndexInput is a class that encapsulates exceptions related to an index.
 */

public class InvalidIndexInputException extends Exception {

    String indexString;

    public InvalidIndexInputException(String indexString) {
        super();
        this.indexString = indexString;
    }

    public String getMessage(String indexString) {
        return indexString + " is not a valid index!";
    }
}
