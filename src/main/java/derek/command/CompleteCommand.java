package derek.command;

import derek.Storage;
import derek.Ui;
import derek.exception.IncorrectCommandException;
import derek.task.Task;
import derek.task.TaskList;

/**
 * The {@code CompleteCommand} class marks a task as completed in the task list.
 * It extends the {@code Command} class and executes the command to complete a task.
 */
public class CompleteCommand extends Command {

    private Storage storage;
    private Ui ui;
    private int sizeOfTaskList;
    /**
     * Constructs a {@code CompleteCommand} with the specified user command.
     *
     * @param command the user command input
     */
    public CompleteCommand(String command, Storage storage, Ui ui, int sizeOfTaskList) {

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
        if (taskNumber < 1 || taskNumber > this.sizeOfTaskList) {
            throw new IncorrectCommandException("do you not know how to count??");
        }
        TaskList taskList = storage.getTaskList();
        Task task = taskList.get(taskNumber - 1);
        task.markCompleted();
        return ui.completeTask(task);
    }
}
