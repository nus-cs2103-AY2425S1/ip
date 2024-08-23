public class Todo extends Task {
    public Todo(String taskInfo) {
        super(taskInfo);
    }

    @Override
    public String toString() {
        String s = "[T] ";
        if (this.isDone()) {
            s += "[X] ";
        } else {
            s += "[ ] ";
        }
        s += this.getTaskInfo();
        return s;
    }
}
