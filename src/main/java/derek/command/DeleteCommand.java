package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code DeleteCommand} class removes a task from the task list.
 * It extends the {@code Command} class and executes the command to delete a task.
 */
public class DeleteCommand extends Command {

    Storage storage;
    Ui ui;
    int sizeOfTaskList;
    /**
     * Constructs a {@code DeleteCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public DeleteCommand(String command, Storage storage, Ui ui, int sizeOfTaskList) {
        super(command);
        this.storage = storage;
        this.ui = ui;
        this.sizeOfTaskList = sizeOfTaskList;
    }

    @Override
    public String execute() throws IncorrectCommandException {
        String command = this.getCommand();
        String[] words = command.split("\\s+");
        int taskNumber = Integer.valueOf(words[1]);
        // Explicitly check the condition and throw exception if needed
        if (taskNumber < 1 || taskNumber > this.sizeOfTaskList) {
            throw new IncorrectCommandException("Do you not know how to count?");
        }        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(taskNumber - 1);
        taskList.remove(taskNumber - 1);
        return ui.removeTask(task);
    }
}
