package orionExceptions;

public class InvalidListException extends OrionException{
    public InvalidListException(String input) {
        super("Your input was " + input + " .However, the correct way to use list is to type list to list out all the tasks");
    }
}
