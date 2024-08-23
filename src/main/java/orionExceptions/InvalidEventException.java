package orionExceptions;

public class InvalidEventException extends  OrionException{
    public InvalidEventException(String message) {
        super("You wrote" + message + " . The correct usage is event [description] /from [start time] /to [end time]");
    }
}
