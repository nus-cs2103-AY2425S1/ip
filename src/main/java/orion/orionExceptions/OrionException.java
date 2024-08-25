package orion.orionExceptions;

public class OrionException extends Exception{
    public OrionException(String message) {
        super("The program threw an exception. It is:" + message);
    }
}
