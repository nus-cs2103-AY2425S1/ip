package duke.command;

import java.util.ArrayList;

import duke.Archive;
import duke.Storage;
import duke.TaskList;
import duke.Ui;
import duke.tasks.Task;

/**
 * Represents a find command.
 */
public class FindCommand extends Command {
    /**
     * The keyword to search for.
     */
    private String keyword;

    /**
     * Constructor for a find command.
     *
     * @param keyword The keyword to search for.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Writes the response message listing all tasks matching the keyword.
     *
     * @param tasks The list of tasks to search from.
     * @param ui The ui to write the message.
     * @param storage The storage for the command to interact with.
     * @param archive The archive for the command to interact with.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage, Archive archive) {
        ArrayList<Task> taskArray = tasks.all();
        ArrayList<Task> results = new ArrayList<>();

        for (Task task: taskArray) {
            if (task.toString().toUpperCase().contains(this.keyword.toUpperCase())) {
                results.add(task);
            }
        }

        ui.find(results);
    }
}
