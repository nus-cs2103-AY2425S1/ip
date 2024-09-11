package command;

import main.Storage;
import main.TaskList;
import main.Ui;

/**
 * Represents a command to mark a task as completed.
 */
public class MarkCommand extends Command {
    private final String input;

    /**
     * Constructs a MarkCommand with the specified input.
     *
     * @param input The command input, which includes the index of the task to mark as done.
     */
    public MarkCommand(String input) {
        this.input = input;
    }

    /**
     * Executes the MarkCommand by marking the specified task as completed.
     *
     * @param tasks The TaskList containing the task to be marked.
     * @param storage The Storage object to update the task status in persistent storage.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) {
        String[] parts = this.input.split(" ", 2);
        if (parts.length < 2) {
            return Ui.showMissingMarkIndex;
        }

        try {
            int markNum = Integer.parseInt(parts[1]);
            return storage.markTask(markNum, tasks);
        } catch (NumberFormatException e) {
            return Ui.showNumberFormatExceptionMessage;
        }
    }
}
