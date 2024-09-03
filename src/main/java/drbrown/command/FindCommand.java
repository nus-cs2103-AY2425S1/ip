package drbrown.command;

import drbrown.task.Task;
import drbrown.utils.DrBrownException;
import drbrown.utils.Storage;
import drbrown.utils.TaskList;
import drbrown.utils.Ui;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Objects;

/**
 * Represents a command to find tasks that match a given keyword.
 * Inherits from the Command class.
 */
public class FindCommand extends Command {

    private final String keyword;

    /**
     * Constructs a FindCommand with the specified keyword.
     *
     * @param keyword The keyword to search for in tasks.
     */
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    /**
     * Executes the FindCommand, searching for tasks in the provided TaskList that contain the keyword.
     * If matching tasks are found, they are displayed using the Ui object.
     * If no tasks are found or the list is empty, appropriate messages are shown.
     *
     * @param tasks   The TaskList containing all tasks.
     * @param ui      The Ui object for user interactions.
     * @param storage The Storage object for saving/loading tasks (not used in this command).
     * @throws DrBrownException If there are no tasks in the list to search from.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws DrBrownException {
        if (tasks.getCount() == 0) {
            throw new DrBrownException("Wait a minute, Doc! There's nothing here! We can't go anywhere until you add something to the list!");
        }
        ArrayList<Task> list = tasks.getList();
        boolean isMatching = false;
        int listCount = 1;
        for (Task item : list) {
            String[] description = item.getDescription().split(this.keyword);
            if (!Objects.equals(description[0], item.getDescription())) {
                if (!isMatching) {
                    ui.showFind();
                    isMatching = true;
                }
                System.out.println(listCount + ". " + item.toString());
                listCount++;
            }
        }
        if (!isMatching) {
            System.out.println("Oops! Seems like there are no matching tasks.");
        }
    }

    /**
     * Indicates whether the command causes the application to exit.
     *
     * @return false, since FindCommand does not cause the application to exit.
     */
    @Override
    public boolean isExit() {
        return false;
    }
}
