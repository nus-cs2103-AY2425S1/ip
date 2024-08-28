public class Todo extends Task {

    private Task task;

    public Todo(String description) {
        super(description);
        this.task = new Task(this.description);
    }

    @Override
    public String markTask() {
        return String.format("[T] %s", this.task.markTask());
    }

    @Override
    public String unmarkTask() {
        return String.format("[T] %s", this.task.unmarkTask());
    }

    @Override
    public String toString() {
        return String.format("[T] %s", this.task.toString());
    }

}
