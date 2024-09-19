package jude.command;

import jude.JudeException;
import jude.Storage;
import jude.TaskList;

/**
 * Represents the class which contains the series of actions to mark a Task as done to be executed.
 */
public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    /**
     * Marks the task as done. Save the changes into the save file.
     * @param list
     * @param storage will save the marked version of task data to the save file
     * @throws JudeException if any one of the method call fails
     */
    @Override
    public void execute(TaskList list, Storage storage) throws JudeException {
        list.markTask(index);
        setMessage("Task " + list.getTask(index) + " has been marked.");
        storage.save(list);
    }

    @Override
    public String getType() {
        return "MarkCommand";
    }

}
