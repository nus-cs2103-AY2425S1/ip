package fred.Exceptions;

/**
 * The EmptyKeywordsException class represents an exception that is thrown when
 * the user does not provide any keywords for a search operation.
 */
public class EmptyKeywordsException extends FredException {

    /**
     * Constructs an EmptyKeywordsException with a default error message indicating
     * that the user has not specified any keywords to search for.
     */
    public EmptyKeywordsException() {
        super("OOPS!!! Looks like you forgot to specify the keywords to look for");
    }
}
