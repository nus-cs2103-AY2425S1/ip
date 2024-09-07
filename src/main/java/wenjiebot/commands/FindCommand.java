package wenjiebot.commands;

import java.util.ArrayList;

import wenjiebot.Storage;
import wenjiebot.TaskList;
import wenjiebot.Ui;
import wenjiebot.exceptions.WenJieException;
import wenjiebot.tasks.Task;



/**
 * The FindCommand class represents a command that searches for tasks containing a specified keyword in their
 *      description.
 * It extends the Command class to implement the functionality for finding and displaying matching tasks.
 */
public class FindCommand extends Command {

    /**
     * Constructs a FindCommand with the specified exit status and input.
     * The exit status is set to false, indicating that this command does not terminate the application.
     *
     * @param isExit A boolean indicating if the command should exit the application. This is set to false
     *      for FindCommand.
     * @param input The input string containing the search keyword.
     */
    public FindCommand(boolean isExit, String input) {
        super(false, input);
    }

    /**
     * Executes the FindCommand to search for tasks containing the specified keyword in their description.
     * The matching tasks are then displayed to the user.
     *
     * @param tasks The TaskList object containing the list of tasks.
     * @param ui The Ui object responsible for user interaction.
     * @param storage The Storage object for reading and writing tasks (not used in this command).
     * @throws WenJieException If an error occurs during execution.
     */
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws WenJieException {
        String[] parts = getInput().split(" ");
        ArrayList<Task> taskList = tasks.getTasks();

        ArrayList<Task> result = new ArrayList<>();
        for (Task task : taskList) {
            if (task.getDescription().contains(parts[1])) {
                result.add(task);
            }
        }

        ui.showLine();
        System.out.println(displayList(result));
        ui.showLine();
        ui.setOutput(displayList(result));
    }

    /**
     * Formats a list of tasks into a string representation for display.
     * Each task is numbered and displayed on a new line.
     *
     * @param list The list of tasks to be formatted.
     * @return A string representation of the tasks for display.
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
