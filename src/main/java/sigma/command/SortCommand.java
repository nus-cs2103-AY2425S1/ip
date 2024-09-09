package sigma.command;

import sigma.exception.SigmaException;
import sigma.utils.Storage;
import sigma.utils.TaskList;
import sigma.utils.Ui;

/**
 * Represents the command to sort tasks by a parameter.
 */
public class SortCommand extends Command {
    /**
     * Constructor for SortCommand.
     *
     * @param commandArray Command array from user input.
     */
    public SortCommand(String[] commandArray) {
        super(commandArray);
    }

    @Override
    public String execute(TaskList tasks, Ui ui, Storage storage) throws SigmaException {
        if (commandArray.length < 2) {
            throw new SigmaException("What the sigma? You're missing the sort parameters! "
                    + "Write \"sort <parameter>\"!");
        }
        String parameter = commandArray[1];
        assert parameter != null : "Parameter cannot be null";
        TaskList sortedTasks = new TaskList(tasks.filterTasksByParameter(parameter));
        String taskListString = Ui.buildList(sortedTasks).toString();
        return String.format("You're sorting for \"%s\"? Here you go sis!\n%s", parameter,
                taskListString);
    }
}
