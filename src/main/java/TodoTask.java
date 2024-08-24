public class TodoTask extends Task {

    public TodoTask(String taskDescription) {
        super(taskDescription);
    }

    private TodoTask(boolean isDone, String taskDescription) {
        super(isDone, taskDescription);
    }

    @Override
    public Task markAsDone() {
        return super.isDone
            ? this
            : new TodoTask(true, super.taskDescription);
    }

    @Override 
    public Task markAsUndone() {
        return super.isDone
            ? new TodoTask(super.taskDescription)
            : this;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

}
