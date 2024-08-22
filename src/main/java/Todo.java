public class Todo extends Task {
    private final String taskType = "T";
    public Todo(String name, boolean completed, int index) {
        super(name, completed, index);
    }

    @Override
    public String toString() {
        return this.getIndex() + ". [" + this.getTaskType() + "]["+ this.checkMark() + "] " + this.getName();
    }

    public String getTaskType() {
        return this.taskType;
    }
}
