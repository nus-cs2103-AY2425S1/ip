public class InvalidTaskException extends Exception{
    private String type;
    public InvalidTaskException(String type) {
        super();
        this.type = type;
    }

    @Override
    public String toString() {
        switch (type) {
            case "todo":
                return """
                         Please enter the description of the todo task you want to create.""";
            case "deadline":
                return """
                         Unmatching input pattern for deadline. Better cut our losses...
                         Please follow the format and add the deadline after "by",
                         eg.deadline return book /by Sunday""";
            case "event":
                return """
                         Unmatching input pattern for event. Better cut our losses...
                         Please follow the format and add the time after "from" and "to",
                         eg.event project meeting /from Mon 2pm /to 4pm""";
            default:
                return "";
        }
    }
}
