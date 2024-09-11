package nimbus.command;

import java.util.ArrayList;

import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

/**
 * This commands finds the task by searching for a keyword in the task description
 */
public class FindCommand extends Command {
    private String userInput;
    private ArrayList<Task> tasks;

    /**
     * Creates a FindCommand object containing the keyword as user input
     * and taskList to search for
     *
     * @param userInput the keyword the user provides
     * @param taskList task list to search in
     */
    public FindCommand(String userInput, TaskList taskList) {
        super(userInput, taskList);
        this.userInput = userInput;
        this.tasks = taskList.getTaskList();
    }

    /**
     * Iterates through the arraylist to find tasks with the keyword
     * Prints out the tasks with the keyword
     */
    @Override
    public String execute() {
        if (userInput.length() < 6) {
            return "Please enter what you want to find"
                    + "\n" + "For example: find tutorial";
        }

        String temp = userInput.substring(4).trim();
        String output = "";
        int counter = 1;
        for (Task task : tasks) {
            if (task.getTaskName().contains(temp)) {
                output += counter + ". " + task + "\n";
                counter++;
            }
        }
        output += ("Here are your results :3" + Ui.HORIZONTAL_LINE);
        return output;
    }
}
