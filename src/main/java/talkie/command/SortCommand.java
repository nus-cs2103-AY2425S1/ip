package talkie.command;

import talkie.components.Storage;
import talkie.components.Ui;
import talkie.exception.TalkieException;
import talkie.task.TaskList;

/**
 * Represents a command to sort the list of tasks.
 * This command sorts the tasks by their description and displays the sorted list.
 */
public class SortCommand extends Command {

    private final String fullCommand;

    public SortCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws TalkieException {

        // Sort list of tasks
        tasks.sortByDescription();
        return ui.listSortedTasks(tasks);
    }


    @Override
    public boolean isExit() {
        return false;
    }
}
