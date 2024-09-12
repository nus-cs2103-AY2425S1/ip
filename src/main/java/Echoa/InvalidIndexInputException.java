package echoa;

/**
 * InvalidIndexInput is a class that encapsulates exceptions related to an index.
 * It extends from class EchoaException.
 */

public class InvalidIndexInputException extends EchoaException {

    String indexString;

    public InvalidIndexInputException(String indexString) {
        super();
        this.indexString = indexString;
    }

    public String getMessage(String indexString) {
        return indexString + " is not a valid index!";
    }
}
