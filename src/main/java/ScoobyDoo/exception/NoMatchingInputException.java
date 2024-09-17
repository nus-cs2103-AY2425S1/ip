package ScoobyDoo.exception;

public class NoMatchingInputException extends InputFormatException{
    public NoMatchingInputException() {
        super("No matched input, The available inputs are\n deadline\n " +
                "event\n todo\n mark\n unmark\n list\n delete\n bye");
    }
}
