package chatBot.exception;

public class InvalidCommandException extends Exception{
    public InvalidCommandException(){
        super("commands accepted: todo , deadline , event , list, mark , unmark , bye , delete");
    }
}
