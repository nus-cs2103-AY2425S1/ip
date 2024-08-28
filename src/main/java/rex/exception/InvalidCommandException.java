package exceptions;

public class InvalidCommandException extends InvalidInputException {
    public InvalidCommandException() {
        super("I don't know what that means!!!\nEnter \"help\" for a list of what I do know! rawr");
    }
}
