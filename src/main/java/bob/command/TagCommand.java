package bob.command;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * Tags a task.
 */
public class TagCommand extends Command {

    /**
     * Constructor to initialise TagCommand
     *
     * @param input
     */
    public TagCommand(String input) {
        super(input);
    }


    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = getInput();
        try {
            taskList.tagTaskInTaskList(input);
            storage.saveTaskListToStorage(taskList);
            String tagTaskConfirmationMessage = taskList.getTaggedTaskString(input);
            Ui.showTaggedTask(tagTaskConfirmationMessage);
            return tagTaskConfirmationMessage;
        } catch (InvalidTaskException e) {
            System.err.println((e.getMessage()));
            return e.getMessage();
        }
    }
}
