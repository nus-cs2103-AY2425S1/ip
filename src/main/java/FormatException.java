public class FormatException extends DukeException {
    public FormatException(String taskType) {
        super(String.format("The format for &s is not suitable.", taskType));
    }
}
