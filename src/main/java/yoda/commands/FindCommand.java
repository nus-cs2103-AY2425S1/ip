package yoda.commands;

import yoda.TaskList;
import yoda.exceptions.InvalidInputException;
import yoda.tasks.Task;

import java.util.ArrayList;

/**
 * Represents a command to find tasks from task list that contain
 * a keyword
 */
public class FindCommand extends Command {
    private TaskList taskList;
    private String input;

    /**
     * Constructs a FindCommand with the specified task list and input.
     *
     * @param taskList The task list to search within.
     * @param input  The user input provided.
     */
    public FindCommand(TaskList taskList, String input) {
        this.taskList = taskList;
        this.input = input;
    }

    /**
     * Executes the FindCommand and displays a list of matching tasks.
     *
     * @throws InvalidInputException if input format is invalid.
     */
    public void run() throws InvalidInputException {
        if (!checkValidInput()) {
            throw new InvalidInputException("Find... what?");
        }
        String[] splitInput = input.split(" ", 2);
        String keyword = splitInput[1];
        ArrayList<Task> matchingTasks = findTasks(keyword);

        System.out.println("Matching tasks:");
        for (int i = 0; i < matchingTasks.size(); i++) {
            System.out.printf("%d. %s\n", i + 1, matchingTasks.get(i));
        }
    }

    /**
     * Finds tasks in the task list that contain the keyword.
     *
     * @param keyword input by user.
     * @return ArrayList of matching tasks.
     */
    private ArrayList<Task> findTasks(String keyword) {
        ArrayList<Task> matchingTasks = new ArrayList<>();
        for (int i = 0; i < taskList.getLength(); i++) {
            Task task = taskList.get(i);
            if (task.getDescription().contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }


    /**
     * Checks if input format is valid.
     *
     * @return true if input format is valid.
     */
    private boolean checkValidInput() {
        String[] splitInput = input.split(" ", 2);
        return splitInput.length == 2;
    }

}
