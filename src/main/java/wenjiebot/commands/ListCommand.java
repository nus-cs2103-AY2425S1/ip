package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.tasks.Task;

/**
 * Represents a command to list all tasks in the task list.
 * This command will display the tasks stored in the task list to the user.
 */
public class ListCommand extends Command {

    /**
     * Constructs a ListCommand with the specified activity status and input.
     *
     * @param isActive boolean indicating whether this command is active.
     * @param input the input associated with this command.
     */
    public ListCommand(boolean isActive, String input) {
        super(isActive, input);
    }

    /**
     * Executes the ListCommand, which retrieves tasks from storage,
     * displays them to the user, and wraps the output with separator lines.
     *
     * @param tasks the TaskList that contains all the tasks (not used in this implementation).
     * @param ui the Ui used for interaction with the user.
     * @param storage the Storage used to store and retrieve tasks.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.showLine();
        System.out.println(displayList(storage.load()));
        ui.showLine();
        ui.setOutput(displayList(storage.load()));
    }

    /**
     * Generates a string representing the list of tasks with their respective indices.
     *
     * @param list the ArrayList of tasks to be displayed.
     * @return a formatted string with each task in the list prefixed by its index.
     */
    public static String displayList(ArrayList<Task> list) {
        String result = "";
        int i = 0;
        while (i < list.size()) {
            String newLine = (i + 1) + ". " + list.get(i) + "\n";
            result += newLine;
            i++;
        }
        return result;
    }
}
