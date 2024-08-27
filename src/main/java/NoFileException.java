public class NoFileException extends BeeBooExceptions{
    public NoFileException(String error) {
        super(error);
    }

    @Override
    public String toString() {
        return "You don't have any data right now.Let me create a new TaskList";
    }
}
