package orion.commands;

import orion.chatbot.Storage;
import orion.chatbot.TaskList;
import orion.chatbot.Ui;

import java.util.List;

/**
 * Represents a command to find and list tasks that match a specific query.
 */
public class FindCommand extends Command {

    /** The query string used to find matching tasks. */
    private String query;

    /**
     * Constructs a {@code FindCommand} with the specified query.
     *
     * @param query the query string to search for in the task descriptions
     */
    public FindCommand(String query) {
        super(false);
        this.query = query;
    }

    /**
     * Executes the find command by searching for tasks that match the query and printing them.
     * The matching tasks are obtained from the {@link TaskList} and their descriptions are printed
     * using the {@link Ui}.
     *
     * @param tasks the {@link TaskList} containing the tasks to search through
     * @param storage the {@link Storage} object used for data storage (not used in this method)
     * @param ui the {@link Ui} object used to print the task descriptions
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) {
        List<String> taskDescriptions = tasks.getMatchingTasks(query);
        ui.printTaskList(taskDescriptions);
    }
}
