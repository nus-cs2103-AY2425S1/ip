package genji.command;

import genji.task.TaskList;
import genji.Ui;
import genji.Storage;

/**
 * A class that deals with find command
 */
public class FindCommand extends Command {
    private String taskDescription;
    private String response;

    /**
     * Constructor of find command
     * @param taskDescription Word concerning which task to be found
     */
    public FindCommand(String taskDescription) {
        this.taskDescription = taskDescription;
    }

    /**
     * Calling the findTask method in TaskList class
     * @param list Task list to be modified
     * @param ui Ui for display
     * @param s Storage for saving
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage s) {
        response = ui.find(list.findTask(taskDescription));
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
