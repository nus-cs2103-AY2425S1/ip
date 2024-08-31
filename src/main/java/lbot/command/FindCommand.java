package lbot.command;

import java.util.ArrayList;

import lbot.exception.ExecuteCommandException;
import lbot.exception.FileException;
import lbot.helper.Storage;
import lbot.helper.TaskList;
import lbot.helper.Ui;
import lbot.task.Task;


/**
 * This class filters through {@link TaskList}.
 */
public class FindCommand extends Command {
    private String searchTerm;

    /**
     * Constructor for FindCommand.
     *
     * @param searchTerm to be searched.
     */
    public FindCommand(String searchTerm) {
        this.searchTerm = searchTerm;
    }

    /**
     * Executes find request by user.
     *
     * @param ui      handles user input and printing.
     * @param storage handles reading and writing to text file.
     * @param tasks   contains list of tasks.
     * @throws ExecuteCommandException thrown if method is unable to create the task.
     * @throws FileException           thrown if there is an error encountered while reading or writing to file.
     */
    @Override
    public void execute(Ui ui, Storage storage, TaskList tasks) throws ExecuteCommandException, FileException {
        StringBuilder sb = new StringBuilder();
        ArrayList<Task> filteredList = tasks.findTask(searchTerm);
        if (filteredList.isEmpty()) {
            sb.append("No tasks found with '").append(searchTerm).append("'");
            sb.append("\n");
        } else {
            int count = 1;
            sb.append("Here are your tasks with the term '").append(searchTerm).append("':\n");
            for (Task task : filteredList) {
                sb.append(count).append(". ");
                sb.append(task).append("\n");
                count++;
            }
        }
        ui.print(sb.toString());
    }
}
