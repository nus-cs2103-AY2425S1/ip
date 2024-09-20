package commands;

import storage.Storage;
import storage.TaskList;
import tasks.Task;

/**
 * Represents a command to find tasks that contain a specific keyword or phrase.
 * The FindCommand class searches through the task list for tasks that match the specified description.
 */
public class FindCommand implements Command {
    private final String description;

    /**
     * Constructs a FindCommand with the specified keyword or phrase to search for.
     *
     * @param description the keyword or phrase to search for in the task descriptions
     */
    public FindCommand(String description) {
        this.description = description;
    }

    /**
     * Executes the find command, searching for tasks in the task list that contain the specified keyword or phrase.
     * If matching tasks are found, they are displayed to the user.
     * If no matches are found, an appropriate message is shown.
     *
     * @param storage the Storage object for handling task persistence
     * @param master the TaskList object containing the list of tasks
     * @return String that denotes a response that is displayed to the user
     */
    @Override
    public String execute(Storage storage, TaskList master) {
        String s = "";
        for (int i = 0; i < master.getSize(); i++) {
            Task task = master.getTasks().get(i);
            if (task.containWord(this.description)) {
                s += "\n" + task;
            }
        }
        String prepend;
        if (!s.isEmpty()) {
            prepend = String.format("Friday > Here are the tasks containing \"%s\"!%n", this.description);
        } else {
            return String.format("Friday > Sorry! There are no tasks containing \"%s\".%n", this.description);
        }
        return prepend + s;
    }
}
