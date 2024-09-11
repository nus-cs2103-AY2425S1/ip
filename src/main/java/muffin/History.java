package muffin;

/**
 * The History class represents an entry in the history stack, storing information
 * about a command that was executed on the task list. Each history entry includes the
 * command that was executed, the task it acted upon, and the index of the task in the list.
 */
public class History {

    /**
     * The command that was executed (e.g., "add", "delete", "mark", "unmark").
     */
    private String command;

    /**
     * The task that the command was executed on.
     */
    private Task task;

    /**
     * The position of the task in the task list at the time the command was executed.
     */
    private int index;

    /**
     * Constructs a new History object to store details about a command executed on a task.
     *
     * @param command The command that was executed.
     * @param task The task that the command was executed on.
     * @param index The position of the task in the task list at the time the command was executed.
     */
    public History(String command, Task task, int index) {
        this.command = command;
        this.task = task;
        this.index = index;
    }

    public String getCommand() {
        return this.command;
    }

    public Task getTask() {
        return this.task;
    }

    public int getIndex() {
        return this.index;
    }
}
