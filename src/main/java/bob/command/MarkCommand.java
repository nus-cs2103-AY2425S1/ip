package bob.command;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class MarkCommand extends Command {
    private boolean isCompleted;

    /**
     * Constructor to initalise MarkCommand
     *
     * @param input
     */
    public MarkCommand(String input, boolean isCompleted) {
        super(input);
        this.isCompleted = isCompleted;
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            taskList.markTaskInTaskList(input, isCompleted);
            storage.saveTaskListToStorage(taskList);
            String markTaskConfirmationMessage = taskList.getMarkedTaskStringFromTaskList(input, isCompleted);
            Ui.showMarkedTask(markTaskConfirmationMessage);
            return markTaskConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println((e.getMessage()));
            return e.getMessage();
        }
    }
}
