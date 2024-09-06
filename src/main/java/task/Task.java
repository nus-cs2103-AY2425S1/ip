package task;

/**
 * Represents a task in the task manager application.
 * A task can have a description and be marked as done or not done.
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected TaskType taskType;

    /**
     * Creates a new task with the description and task type.
     * Task initially set as not done
     *
     * @param description Description of the task.
     * @param taskType Type of the task.
     */
    public Task(String description, TaskType taskType) {
        this.description = description;
        this.isDone = false;
        this.taskType = taskType;
    }

    /**
     * Creates a new task with the description ,task type and if it is done.
     *
     * @param description Description of the task.
     * @param taskType Type of the task.
     * @param isDone Whether task is done.
     */
    public Task(String description, TaskType taskType, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
        this.taskType = taskType;
    }

    /**
     * Returns whether task is done.
     *
     * @return true if the task is done, false otherwise.
     */
    public boolean isDone() {
        return isDone;
    }

    /**
     * Marks the task as done and prints a confirmation message.
     */
    public void markAsDone() {
        System.out.println("     Nice! I've marked this task as done:");
        this.isDone = true;
        System.out.println("       " + this.toString());
    }

    /**
     * Marks the task as done and returns a confirmation message.
     *
     * @return Confirmation message that task has been marked as done.
     */
    public String stringMarkAsDone() {
        this.isDone = true;
        return "     Nice! I've marked this task as done:\n       " + this.toString();
    }

    /**
     * Unmarks the task as not done and prints a confirmation message.
     */
    public void unmark() {
        System.out.println("     OK, I've marked this task as not done yet:");
        this.isDone = false;
        System.out.println("       " + this.toString());
    }

    /**
     * Unmarks the task as not done and returns a confirmation message.
     *
     * @return Confirmation message that task has been unmarked.
     */
    public String stringUnmark() {
        this.isDone = false;
        return "     OK, I've marked this task as not done yet:\n       " + this.toString();
    }

    /**
     * Returns the status icon based on task completion status
     *
     * @return "[X]" if done, "[ ]" if not done.
     */
    public String getStatusIcon() {
        return isDone ? "[X]" : "[ ]";
    }

    /**
     * Returns the type of task.
     *
     * @return Task type as a string.
     */
    public String getType() {
        return "[" + taskType.name().charAt(0) + "]";
    }

    /**
     * Returns a string representation of the task.
     *
     * @return Task details as a string.
     */
    @Override
    public String toString() {
        return getType() + getStatusIcon() + " " + description;
    }

    /**
     * Prints a confirmation message that the task has been added.
     *
     * @param taskCount The current number of tasks in the list.
     */
    public void printTaskAddedMessage(int taskCount) {
        System.out.println("     Got it. I've added this task:");
        System.out.println("       " + this);
        if (taskCount == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Returns a confirmation message that the task has been added.
     *
     * @param taskCount The current number of tasks in the list.
     * @return Confirmation message that task has been added.
     */
    public String stringPrintTaskAddedMessage(int taskCount) {
        StringBuilder message = new StringBuilder();
        message.append("     Got it. I've added this task:\n       ").append(this).append("\n");

        if (taskCount == 1) {
            message.append("     Now you have 1 task in the list.");
        } else {
            message.append("     Now you have ").append(taskCount).append(" tasks in the list.");
        }

        return message.toString();
    }

    /**
     * Prints a confirmation message that the task has been removed.
     *
     * @param taskCount The current number of tasks in the list.
     */
    public void printTaskRemovedMessage(int taskCount) {
        System.out.println("     Noted. I've removed this task:");
        System.out.println("       " + this);
        if (taskCount == 1) {
            System.out.println("     Now you have 1 task in the list.");
        } else {
            System.out.println("     Now you have " + taskCount + " tasks in the list.");
        }
    }

    /**
     * Returns a confirmation message that the task has been removed.
     *
     * @param taskCount The current number of tasks in the list.
     * @return Confirmation message that task has been removed.
     */
    public String stringPrintTaskRemovedMessage(int taskCount) {
        StringBuilder message = new StringBuilder();
        message.append("     Noted. I've removed this task:\n       ").append(this).append("\n");

        if (taskCount == 1) {
            message.append("     Now you have 1 task in the list.");
        } else {
            message.append("     Now you have ").append(taskCount).append(" tasks in the list.");
        }

        return message.toString();
    }
}
