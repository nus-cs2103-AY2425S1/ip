package orionExceptions;

public class InvalidDeleteException extends OrionException {
    public InvalidDeleteException(String input) {
        super("Your input was '" + input + "'. However, the correct way to use delete is: delete <task number>. For example: delete 1");
    }
}
