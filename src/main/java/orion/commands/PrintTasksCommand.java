package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import java.util.List;

/**
 * Represents a command to print the list of tasks.
 * <p>
 * This command retrieves the descriptions of all tasks in the {@link TaskList}
 * and displays them through the user interface.
 * </p>
 */
public class PrintTasksCommand extends Command {

    /**
     * Constructs a {@code PrintTasksCommand}. This command does not signal
     * an exit, so the {@code isExit} flag is set to {@code false}.
     */
    public PrintTasksCommand() {
        super(false);
    }

    /**
     * Executes the command by retrieving the task descriptions from the task list
     * and printing them using the user interface.
     *
     * @param tasks  the {@link TaskList} containing the tasks to be printed
     * @param storage the {@link Storage} for managing the task list (not used in this command)
     * @param ui      the {@link Ui} for updating the user interface
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        List<String> taskDescriptions = tasks.getTaskDescriptions();
        ui.printTaskList(taskDescriptions);
    }
}
