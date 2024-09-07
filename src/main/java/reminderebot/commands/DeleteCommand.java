package reminderebot.commands;

import reminderebot.ReminderebotException;
import reminderebot.Storage;
import reminderebot.TaskList;
import reminderebot.Ui;
import reminderebot.task.Task;

/**
 * The DeleteCommand class represents a command to delete a task in tasklist.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Create a Delete command.
     * @param index
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    /**
     * Delete task.
     * @param tasklist
     * @param ui
     * @param storage
     * @return String representing Delete command
     * @throws ReminderebotException
     */
    @Override
    public String execute(TaskList tasklist, Ui ui, Storage storage) throws ReminderebotException {
        if (index > tasklist.length() || index < 1) { // index is out of bounds
            throw new ReminderebotException("Item selected to be deleted is not in list.\n"
                    + "Syntax: delete <int>");
        }
        Task task = tasklist.deleteTask(index);
        return ui.deleteTask(task, tasklist.length());
    }

    /**
     * Delete command does not exit.
     * @return false
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
