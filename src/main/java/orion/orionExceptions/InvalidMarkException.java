package orion.orionExceptions;

public class InvalidMarkException extends OrionException{
    public InvalidMarkException(String input) {
        super("Your input was " + input + " .However, the correct way to use mark or unmark is to type mark 1 (or unmark 1) to mark the list shown by 1");
    }

}
