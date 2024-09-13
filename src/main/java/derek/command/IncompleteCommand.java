package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;


/**
 * The {@code IncompleteCommand} class marks a task as incomplete in the task list.
 * It extends the {@code Command} class and executes the command to mark a task as incomplete.
 */
public class IncompleteCommand extends Command {

    private Storage storage;
    private Ui ui;
    private int sizeOfTaskList;

    /**
     * Constructs an {@code IncompleteCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public IncompleteCommand(String command, Storage storage, Ui ui, int sizeOfTaskList) {

        super(command);
        this.storage = storage;
        this.ui =ui;
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
        }

        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(taskNumber - 1);
        task.markIncomplete();
        return ui.incompleteTask(task);
    }
}
