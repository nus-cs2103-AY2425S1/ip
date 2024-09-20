package secondmind;

public class EmptyTaskDescriptionException extends Exception {
    @Override
    public String toString() {
        return "Warning! Description of a task cannot be empty!";
    }
}