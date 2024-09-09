package conversage.task;

import conversage.exception.ConverSageException;

/**
 * Represents a task.
 */
public abstract class Task {
    protected String taskDesc;
    protected boolean isDone;


    /**
     * Constructs a Task with the specified description.
     *
     * @param taskDesc the description of the task.
     */
    Task(String taskDesc) {
        this.taskDesc = taskDesc;
        this.isDone = false;
    }

    /**
     * Marks the task as done.
     *
     * @return the string representation of the task marked as done.
     * @throws ConverSageException if the task is already marked as done.
     */
    public String markAsDone() throws ConverSageException {
        if (this.isDone) {
            throw new ConverSageException("This task is already marked as done...");
        }
        this.isDone = true;
        return "[X] " + this.taskDesc;
    }

    /**
     * Marks the task as not done.
     *
     * @return the string representation of the task marked as not done.
     * @throws ConverSageException if the task is already marked as not done.
     */
    public String markAsUndone() throws ConverSageException {
        if (!this.isDone) {
            throw new ConverSageException("This task is already marked as not done...");
        }
        this.isDone = false;
        return "[ ] " + this.taskDesc;
    }

    /**
     * Gets the status of the task.
     *
     * @return the status of the task as a string.
     */
    public String getStatus() {
        return this.isDone ? "[X]" : "[ ]";
    }

    /**
     * Converts the task to a file format string.
     *
     * @return the file format string of the task.
     */
    public abstract String toFileFormat();


    /**
     * Creates a task from a file format string.
     *
     * @param fileFormatString the file format string of the task.
     * @return the task created from the file format string.
     * @throws ConverSageException if the file format string is invalid.
     */
    public static Task fromFileFormat(String fileFormatString) throws ConverSageException {
        // Note: 2 escape char used
        String[] parts = fileFormatString.split(" \\| ");
        String taskType = parts[0];
        boolean isDone = parts[1].equals("Done");
        String taskDesc = parts[2];

        switch (taskType) {
            case "conversage.task.ToDo":
                Task newTodo = new ToDo(taskDesc);
                if (isDone) {
                    newTodo.markAsDone();
                }
                return newTodo;

            case "conversage.task.Event":
                Task newEvent = new Event(taskDesc, parts[3], parts[4]);
                if (isDone) {
                    newEvent.markAsDone();
                }
                return newEvent;

            case "conversage.task.Deadline":
                Task newDeadline = new Deadline(taskDesc, parts[3]);
                if (isDone) {
                    newDeadline.markAsDone();
                }
                return newDeadline;

            default:
                throw new ConverSageException("Unknown conversage.task.Task Type: " + taskType);
        }
    }

    @Override
    public String toString() {
        return this.getStatus() + " " + this.taskDesc;
    }
}
