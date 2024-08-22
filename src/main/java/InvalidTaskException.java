public class InvalidTaskException extends Exception {
    private String taskType;
    private String baseMessage;
    public InvalidTaskException(String taskType, String baseMessage) {
        super();
        this.taskType = taskType;
        this.baseMessage = baseMessage;
    }

    @Override
    public String getMessage() {
        String errorMessage = baseMessage + "\n";
        switch (taskType) {
            case "todo":
                errorMessage += " To add a todo task, I need the task description\n"
                        + " e.g. todo read CS2103T notes";
                break;
            case "deadline":
                errorMessage += " I need one description and deadline using \"/by\"\n"
                        + " e.g. deadline CS2103T project /by Dec 31st";
                break;
            case "event":
                errorMessage += " I need one description, start and end time using \"/from\" and \"/to\"\n"
                        + " e.g. event project meeting /from Mon 2pm /to 4pm";
        }
        return errorMessage;
    }
}
