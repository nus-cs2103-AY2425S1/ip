package orionExceptions;

public class InvalidMarkException extends OrionException{
    public InvalidMarkException(String input) {
        super("Your input was " + input + " .However, the correct way to use mark  is to type mark 1 to mark the list shown by 1");
    }

}
