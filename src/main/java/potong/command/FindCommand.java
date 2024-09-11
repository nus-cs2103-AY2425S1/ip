package potong.command;

import potong.Storage;
import potong.TaskList;
import potong.Ui;

import potong.exceptions.PotongException;

import java.io.IOException;

/**
 * Represent the find command to find tasks with a keyword.
 */
public class FindCommand extends Command {

    private String keyword;

    /**
     * Initialise find command.
     * @param command Find keyword.
     */
    public FindCommand(String command) {
        super(command);
        this.keyword = command;
    }

    /**
     * Find the tasks with the keyword in the list of tasks.
     *
     * @param tasks List of tasks.
     * @param storage Storage class for saving and loading.
     * @param ui Ui class for input and output.
     * @return String representation of the action.
     */
    @Override
    public String execute(TaskList tasks, Storage storage, Ui ui) {
        return tasks.find(this.keyword);
    }
}
