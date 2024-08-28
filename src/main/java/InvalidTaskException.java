public class InvalidTaskException extends Exception{
    private Topaz.TaskType type;
    public InvalidTaskException(Topaz.TaskType type) {
        super();
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case T:
                return """
                         Please enter the description of the todo task you want to create.""";
            case D:
                return """
                         Unmatching input pattern for deadline. Better cut our losses...
                         Please follow the format and add the deadline after "/by" in yyyy-mm-dd HH:mm format,
                         eg.deadline return book /by 2024-08-28 17:03""";
            case E:
                return """
                         Unmatching input pattern for event. Better cut our losses...
                         Please follow the format and add the time after "/from" and "/to",
                         with time input in format yyyy-mm-dd HH:mm,
                         eg.event project meeting /from 2024-08-28 09:03 /to 2024-08-28 12:30""";
            default:
                return "";
        }
    }
}
