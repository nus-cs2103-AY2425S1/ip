public class TaskIndexException extends IllegalInputException {
    public TaskIndexException(String offender) {
        super("The index you have selected is not allowed:",
                offender,
                "Please select a valid index.");
    }
}
