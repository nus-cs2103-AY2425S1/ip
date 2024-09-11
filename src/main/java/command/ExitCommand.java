package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import task.TaskList;

/**
 * Represents a command to exit the program.
 */
public class ExitCommand extends Command {

    /**
     * Creates an ExitCommand object.
     */
    public ExitCommand() {
        super.isExit = true;
    }

    /**
     * Exits the program.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        return ui.beautifyMessage(" Bye. Hope to see you again soon!");
    }
}
