public class EmptyToDoException extends Exception {
    @Override
    public String toString() {
        return "Warning! Description of a todo task cannot be empty!";
    }
}