package tecna;

/**
 *
 */
public class JsonLoadingException extends Exception {


    public JsonLoadingException(JsonLoadingExceptionType exceptionType) {
        super(exceptionType.message);
    }
}
