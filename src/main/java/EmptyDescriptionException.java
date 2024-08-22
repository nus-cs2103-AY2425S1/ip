public class EmptyDescriptionException extends ScheduloException {
    
    public EmptyDescriptionException(String taskType) {
        super("The description of a " + taskType + " cannot be empty.");
    }
}