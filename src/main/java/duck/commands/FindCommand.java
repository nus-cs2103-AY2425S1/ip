package duck.commands;

import duck.data.TaskList;
import duck.data.exception.DuckException;
import duck.storage.Storage;
import duck.ui.Ui;

/**
 * Represents a command to find tasks that contain a specified keyword.
 * The command searches through the existing tasks in the task list and
 * returns tasks that match the keyword.
 */
public class FindCommand extends Command {

    private static final String ERROR_MESSAGE_FIND_COMMAND = "Quack, you need to provide a keyword to search for!\n";
    private static final String TASKS_NOT_FOUND = "Quack, there are no tasks matching that keyword!";
    private static final String TASKS_FOUND = "Quack, I found these related tasks for you!";


    /**
     * Constructs a FindCommand object with the specified message.
     *
     * @param message The message containing the keyword to search for.
     */
    public FindCommand(String message) {
        super(message);
    }

    /**
     * Executes the find command, searching for tasks in the task list that contain
     * the specified keyword. If the keyword is not provided, an exception is thrown.
     * The matching tasks are printed to the user interface.
     *
     * @param tasks The task list containing all tasks.
     * @param storage The storage object that handles loading and saving tasks.
     * @param ui The user interface that handles interactions with the user.
     * @throws DuckException If the keyword is not provided or an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Storage storage, Ui ui) throws DuckException {
        String keyword = getKeyword();
        TaskList matchingTasks = tasks.findTasks(keyword);
        printFindResult(matchingTasks);
    }

    private void printFindResult(TaskList matchingTasks) {
        if (matchingTasks.isEmpty()) {
            System.out.println(TASKS_NOT_FOUND);
        } else {
            System.out.println(TASKS_FOUND);
            matchingTasks.printTasks();
        }
    }

    private boolean isCorrectFindFormat() {
        return message.length() > 5;
    }

    private String getKeyword() throws DuckException {
        if (!isCorrectFindFormat()) {
            throw new DuckException(ERROR_MESSAGE_FIND_COMMAND);
        }
        return message.substring(5);
    }

    /**
     * Indicates whether this command will exit the program.
     *
     * @return {@code false}, as the find command does not cause the program to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }

}
