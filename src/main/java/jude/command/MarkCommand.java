package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;

/**
 * Represents the class which contains the series of actions to mark a Task as done to be executed.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }
    @Override

    /**
     * Marks the task as done. Save the changes into the save file.
     * @param list
     * @param ui displays the message that a task has been marked
     * @param storage will save the marked version of task data to the save file
     * @throws JudeException if any one of the method call fails
     */
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.markTask(index);
        setMessage("Task " + list.getTask(index) + " has been marked.");
        ui.showMessage(getMessage());
        storage.save(list);
    }

    @Override
    public String toString() {
        return "MarkCommand";
    }
}
