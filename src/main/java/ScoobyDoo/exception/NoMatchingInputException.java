package ScoobyDoo.exception;

public class NoMatchingInputException extends InputFormatException{
    public NoMatchingInputException() {
        super("""
                No matched input, The available inputs are
                 deadline
                 event
                 todo
                 mark
                 unmark
                 list
                 delete
                 bye
                 undo
                 redo""");
    }
}
