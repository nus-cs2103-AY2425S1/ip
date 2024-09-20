package bob.command;

import bob.exception.InvalidTaskException;
import bob.parser.Parser;
import bob.storage.Storage;
import bob.task.TaskList;
import bob.ui.Ui;

/**
 * ListCommand class executes list command.
 */
public class ListCommand extends Command {

    /**
     * Constructor to initialize ListCommand
     */
    public ListCommand(String input) {
        super(input);
    }

    @Override
    public String execute(TaskList taskList, Storage storage, Ui ui) {
        String input = this.getInput();
        try {
            this.verifyOnlyListCommand(input);
            String taskListString = taskList.getListRecordsString();
            ui.showList(taskListString);
            return taskListString;
        } catch (InvalidTaskException e) {
            System.err.println(e.getMessage());
            return e.getMessage();
        }
    }

    /**
     * Verifies that only 1 list command is used.
     *
     * @throws InvalidTaskException
     */
    private void verifyOnlyListCommand(String input) throws InvalidTaskException {
        String[] inputArray = Parser.parseInputIntoStringArray(input);
        if (inputArray.length != 1) {
            throw new InvalidTaskException("list command just needs the keyword 'list'.");
        }
    }
}
