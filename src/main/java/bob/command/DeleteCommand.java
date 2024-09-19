package bob.command;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class DeleteCommand extends Command {

    /**
     * Constructor to initalise DeleteCommand
     *
     * @param input
     */
    public DeleteCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            taskList.deleteTaskFromTaskList(input);
            storage.saveTaskListToStorage(taskList);
            String deletedTaskConfirmationMessage = taskList.getDeletedTaskString();
            Ui.showDeletedTask(deletedTaskConfirmationMessage);
            return deletedTaskConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println((e.getMessage()));
            return e.getMessage();
        }
    }
}
