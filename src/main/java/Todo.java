public class Todo extends Task {
    private final String taskType = "T";
    public Todo(String name, boolean completed) {
        super(name, completed);
    }

    @Override
    public String toString() {
        return "[" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName();
    }

    public String getTaskType() {
        return this.taskType;
    }
}
