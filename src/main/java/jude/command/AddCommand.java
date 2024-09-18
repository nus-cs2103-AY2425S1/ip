package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;
import jude.Ui;
import jude.task.Task;

/**
 * Represents the command which contains the instruction as series of actions to add a Task
 * into the TaskList to be executed.
 */
public class AddCommand extends Command {
    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    /**
     * Adds task to the TaskList. Notifies the user that the task has been added.
     * @param list that will store the task added.
     * @param ui displays the message that the task has been added
     * @param storage will write the task data onto the save file
     * @throws JudeException if any one of the method fails
     */
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) throws JudeException {
        list.addTask(task);
        setMessage("Task " + task + " has been added.");
        storage.save(list);
        ui.showMessage(getMessage());
    }

    @Override
    public String toString() {
        return "AddCommand";
    }

}
