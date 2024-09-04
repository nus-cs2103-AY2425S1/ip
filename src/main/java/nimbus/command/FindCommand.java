package nimbus.command;

import nimbus.exception.WrongDateTimeFormatException;
import nimbus.task.Task;
import nimbus.ui.TaskList;
import nimbus.ui.Ui;

import java.util.ArrayList;

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
    public void execute() {
        if (userInput.length() < 6) {
            System.out.println("Please enter what you want to find" +
                    "\n" + "For example: find tutorial");
            return;
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
        System.out.println(output + "Here are your results :3" + Ui.horizontalLine);
    }
}
