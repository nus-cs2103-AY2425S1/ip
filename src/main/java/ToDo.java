public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String getTaskType() {
        return "T";
    }

    @Override
    public String toString() {
        return "[" + getTaskType() + "]" + super.toString();
    }

    @Override
    public String getFileString() {
        return getTaskType() + " | " + (getIsDone() ? "1" : "0") + " | " + getDescription();
    }
}