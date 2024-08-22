public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    public String getTask() {
        String[] startArr = super.getDescription().split(" ", 2);
        return startArr[1];
    }

    @Override
    public String toString() {
            return "[T]" + "[" + super.getStatusIcon() + "] " + getTask();
    }
}
