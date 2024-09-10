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
        String input = this.getInput();
        String toPrint = "";
        try {
            String[] separateKeyword = input.split(" ", 2); //separate the keyword from the rest of string
            if (separateKeyword.length == 1) {
                throw new InvalidTaskException("OOPS!!! The description of delete cannot be empty.");
            }
            int taskIndex = Integer.parseInt(separateKeyword[1]);
            if (!taskList.isValidRecord(taskIndex)) {
                throw new InvalidTaskException("Invalid input. Integer required between range of record items.");
            }
            toPrint = taskList.getDeletedTaskString(taskIndex);
            taskList.removeRecord(taskIndex);
            storage.saveRecordsToStorage(taskList.getAllRecords());
            ui.showDeletedTask(toPrint);
        } catch (InvalidTaskException e) {
            System.err.println((e.getMessage()));
        }
        return toPrint;
    }
}
