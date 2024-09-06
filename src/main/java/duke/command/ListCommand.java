package duke.command;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;

/**
 * Represents a list command.
 */
public class ListCommand extends Command {
    /**
     * Constructor for a list command.
     */
    public ListCommand() {
    }

    /**
     * Writes the response message listing all tasks in the task list.
     *
     * @param tasks The list of tasks to get the tasks from.
     * @param ui The ui to write the response message.
     * @param storage The storage for the command to interact with.
     * @param archive The archive for the command to interact with.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {
        ui.list(tasks.all());
    }
}
