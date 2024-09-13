package bob.command;

import bob.exception.InvalidTaskException;
import bob.storage.Storage;
import bob.task.Task;
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
        String input = this.getInput();
        String[] inputWords = input.split("\s+");
        Task currTask = null;
        try {
            if (inputWords.length == 1) {
                throw new InvalidTaskException("Please input which item number you want to mark.");
            }
            int taskIndex = Integer.valueOf(inputWords[1]);
            if (!taskList.isValidRecord(taskIndex)) {
                throw new InvalidTaskException("Item index out of range.");
            }
            currTask = taskList.getIndexedTask(taskIndex);
            currTask.mark1Task(isCompleted);
        } catch (InvalidTaskException e) {
            return e.getMessage();
        }
        if (currTask != null) {
            storage.saveRecordsToStorage(taskList.getAllRecords());
            ui.showMarkedTask(currTask, isCompleted);
        }
        return ui.showLine() + currTask.getMarkedTask(isCompleted) + ui.showLine();
    }
}
