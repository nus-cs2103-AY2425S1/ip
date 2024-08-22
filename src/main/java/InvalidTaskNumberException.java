public class InvalidTaskNumberException extends ScheduloException {
    
    public InvalidTaskNumberException() {
        super("Invalid task number given. Type 'list' to find out the task number.");
    }
}