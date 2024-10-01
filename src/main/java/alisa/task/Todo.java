package alisa.task;

public class Todo extends Task {

    /**
     * Constructs an instance of Todo.
     *
     * @param taskDescription Description of the todo task.
     */
    public Todo(String taskDescription) {
        super(taskDescription);
    }

    /**
     * {@inheritDoc}
     *
     * Returns type of task (todo task).
     *
     * @return String indicating it is a todo task.
     */
    @Override
    public String toString() {
        String task = super.toString();
        return "[T] " + task;
    }

    /**
     * {@inheritDoc}
     *
     * Returns details of the todo task.
     *
     * @return String of task type, task status, and task description.
     */
    @Override
    public String convertToFileString() {
        return "T | " + this.getFileStatus()
                + " | " + this.getTask() + "\n";
    }
}
