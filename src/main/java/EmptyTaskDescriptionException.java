public class EmptyTaskDescriptionException extends Exception{
    private String taskType;
    public EmptyTaskDescriptionException(String taskType) {
        super();
        this.taskType = taskType;
    }

    @Override
    public String getMessage() {
        return String.format("Oops...The description of a %s cannot be empty.\nPlease try again...",
                this.taskType );
    }
}
