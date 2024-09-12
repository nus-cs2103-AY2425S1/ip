package tasks;

/**
 * Represents a general Task in Chatterbox.
 */
public class Task {
    private final String name;
    private Boolean isCompleted;

    /**
     * Initialisation of a general Task in Chatterbox
     * @param name Name of a Task.
     */
    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    /**
     * Sets the status of the Task to a specific state.
     * @param completed The state of the Task to update to.
     * @return String of the status of the change.
     */
    public String setCompleted(Boolean completed) {
        StringBuilder message = new StringBuilder();
        if (!this.isCompleted && completed) {
            this.isCompleted = true;
            message.append("____________________________________________________________\n");
            message.append("Nice! I've marked this task as done: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        } else if (this.isCompleted && !completed) {
            this.isCompleted = false;
            message.append("____________________________________________________________\n");
            message.append("Ok, I've marked this task as not done yet: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        } else if (this.isCompleted && completed) {
            message.append("____________________________________________________________\n");
            message.append("This task is already completed: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        } else {
            message.append("____________________________________________________________\n");
            message.append("This task is not completed: ");
            message.append(this).append("\n");
            message.append("____________________________________________________________\n");
        }
        return message.toString();
    }

    public Boolean getCompleted() {
        return isCompleted;
    }

    public String getName() {
        return name;
    }

    /**
     * The general way a Task should be saved as in a save file.
     * @return String for saving a Task.
     */
    public String storeTask() {
        StringBuilder saveTaskInfo = new StringBuilder();
        if (this.getCompleted()) {
            saveTaskInfo.append("done, ");
        } else {
            saveTaskInfo.append("undone, ");
        }
        saveTaskInfo.append("task ");
        saveTaskInfo.append(this.getName());
        saveTaskInfo.append("\n");
        return saveTaskInfo.toString();
    }

    /**
     * The string representation of a Task.
     * @return String representation of a Task.
     */
    @Override
    public String toString() {
        return this.isCompleted ? "[X] " + this.name : "[ ] " + this.name;
    }
}
