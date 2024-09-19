package bob.command;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Tags a task.
 */
public class UntagCommand extends Command {

    /**
     * Constructor to initialise TagCommand
     *
     * @param input
     */
    public UntagCommand(String input) {
        super(input);
    }


    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            taskList.untagTaskInTaskList(input);
            storage.saveTaskListToStorage(taskList);
            String untagTaskConfirmationMessage = taskList.getUntaggedTaskString(input);
            Ui.showTaggedTask(untagTaskConfirmationMessage);
            return untagTaskConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println((e.getMessage()));
            return e.getMessage();
        }
    }
}
