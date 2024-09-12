package command;

import assertions.AssertCommand;
import components.Storage;
import components.TaskListHistory;
import components.Ui;
import exceptions.LightException;
import task.TaskList;

/**
 * Represents a command to redo the last undone command.
 */
public class RedoCommand extends Command {
    public RedoCommand() {
        super();
    }

    /**
     * Redoes the last undone command.
     *
     * @param tasks           The task list.
     * @param ui              The user interface.
     * @param storage         The storage.
     * @param taskListHistory The task list history.
     * @return The message to be displayed to the user.
     * @throws LightException If an error occurs during execution.
     */
    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage, TaskListHistory taskListHistory) throws LightException, LightException {
        new AssertCommand(tasks, ui, storage).assertExecute(tasks, ui, storage);
        TaskList oldTasks = taskListHistory.getNextState();
        tasks.clear();
        tasks.addAll(oldTasks);
        String stringOfTask = TaskList.arrayToNumberedString(tasks);
        storage.write(stringOfTask);
        return ui.beautifyMessage("Got it. I've redone the last command.\n" + stringOfTask);
    }
}
