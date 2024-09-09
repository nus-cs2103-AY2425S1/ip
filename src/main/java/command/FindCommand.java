package command;

import java.io.IOException;

import exception.ScheduloException;
import task.TaskList;
import util.Storage;

/**
 * Represents a command to find tasks that contain a specific word.
 */
public class FindCommand extends Command {

    private String word;

    /**
     * Constructs a FindCommand with the specified search word.
     *
     * @param word The word to search for in task names.
     */
    public FindCommand(String word) {
        this.word = word;
    }

    /**
     * Executes the command to find and display tasks containing the specified word.
     *
     * @param tasks   The TaskList to search in.
     * @param storage The Storage instance for saving and loading tasks.
     * @throws ScheduloException If an application-specific error occurs during execution.
     * @throws IOException       If an I/O error occurs while saving the task list.
     */
    @Override
    public String execute(TaskList tasks, Storage storage) throws ScheduloException, IOException {
        return tasks.find(word);
    }
}
