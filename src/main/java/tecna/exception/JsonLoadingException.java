package tecna.exception;

/**
 * Indicates errors during parsing json data into valid classes.
 *
 * @author DennieDan.
 */
public class JsonLoadingException extends Exception {

    /**
     * Constructs a JsonLoadingException based on the type.
     *
     * @param exceptionType type of json parsing exception.
     */
    public JsonLoadingException(JsonLoadingExceptionType exceptionType) {
        super(exceptionType.message);
    }
}
