public class TodoTask extends Task {
    public TodoTask(String description) throws TaskArgumentMissingException {
        super(description);
    }

    @Override
    public String getEmptyDescriptionErrorMessage() {
        return "I think you tried TODO nothing.\nI can only help to remind you of something todo.";
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
