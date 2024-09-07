package genji.command;

import genji.task.Task;
import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with adding command
 */
public class AddCommand extends Command {
    private Task task;
    private String response;

    /**
     * Constructor of adding command
     * @param task Task to be added
     */
    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Calling the add method in task list class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        list.add(task);
        response = ui.add(task, list);
        s.saveList(list);
    }

    /**
     * Distinguishes if it is a bye command
     * @return Does not exit
     */
    @Override
    public boolean isExit() {
        return false;
    }

    /**
     * Get response message for GUI
     * @return Formatted string
     */
    @Override
    public String getResponse() {
        return response;
    }
}
