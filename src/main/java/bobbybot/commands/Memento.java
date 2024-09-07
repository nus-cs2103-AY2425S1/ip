package bobbybot.commands;

import bobbybot.TaskList;

/**
 * Represents a memento for commands.
 * This is used to store the state of a TaskList before a command is executed.
 */
public class Memento {
    private final TaskList taskList;

    /**
     * Creates a memento with the given taskList.
     *
     * @param taskList The taskList to save.
     */
    public Memento(TaskList taskList) {
        this.taskList = new TaskList();
        this.taskList.copyOver(taskList);
    }

    public TaskList getTaskList() {
        return taskList;
    }
}
