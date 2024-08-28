package revir.system.Exceptions;

public class IllegalCommandException extends Exception{
    public IllegalCommandException(String command) {
        super("\"" + command + "\" is not a valid command");
    }
}
