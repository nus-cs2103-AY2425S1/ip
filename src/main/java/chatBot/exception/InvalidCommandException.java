package chatBot.exception;

/** Exception to be thrown when invalid commands are encountered */
public class InvalidCommandException extends Exception{
    public InvalidCommandException(){
        super("commands accepted: todo , deadline , event , list, mark , unmark , bye , delete");
    }
}
