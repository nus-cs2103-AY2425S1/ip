package duck.commands;

import java.util.concurrent.atomic.AtomicInteger;

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
        super.execute(tasks, storage, ui);

        if (message.length() <= 5) {
            throw new DuckException("Quack, you need to provide a keyword to search for!\n");
        }

        String keyword = message.substring(5);
        TaskList matchingTasks = tasks.findTasks(keyword);

        if (matchingTasks.isEmpty()) {
            System.out.println("Quack, there are no tasks matching that keyword!");
        } else {
            System.out.println("Quack, I found these related tasks for you!");
            AtomicInteger idx = new AtomicInteger(1);
            matchingTasks.forEach(task -> System.out.println(idx.getAndIncrement() + "." + task.toString()));
        }

        System.out.println();
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
