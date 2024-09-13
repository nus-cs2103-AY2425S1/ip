package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;
import ui.UI;

/**
 * Represents a command to find tasks that contain a specific keyword or phrase.
 * The FindCommand class searches through the task list for tasks that match the specified description.
 */
public class FindCommand implements Command {
    private final String desc;

    /**
     * Constructs a FindCommand with the specified keyword or phrase to search for.
     *
     * @param desc the keyword or phrase to search for in the task descriptions
     */
    public FindCommand(String desc) {
        this.desc = desc;
    }

    /**
     * Executes the find command, searching for tasks in the task list that contain the specified keyword or phrase.
     * If matching tasks are found, they are displayed to the user.
     * If no matches are found, an appropriate message is shown.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @param ui the UI object for interacting with the user
     * @return false, indicating that the application should not terminate
     */
    @Override
    public boolean execute(Storage storage, TaskList master, UI ui) {
        String s = "";
        for (int i = 0; i < master.getSize(); i++) {
            Task task = master.getParent().get(i);
            if (task.containsWord(this.desc)) {
                s += "\n" + task.toString();
            }
        }
        if (!s.isEmpty()) {
            System.out.printf("Friday > Here are the tasks containing \"%s\"!%n", this.desc);
            System.out.println(s);
        } else {
            System.out.printf("Friday > Sorry! There are no tasks containing \"%s\".%n", this.desc);
        }
        UI.printLine();
        return false;
    }
}
