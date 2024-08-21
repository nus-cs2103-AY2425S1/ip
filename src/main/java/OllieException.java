public class OllieException extends Exception{
    public OllieException(String errorMessage) {
        super("OOPS!!! " + errorMessage);
    }
}
