package yoda.commands;

import java.util.ArrayList;

import yoda.TaskList;
import yoda.exceptions.YodaException;
import yoda.tasks.Task;

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
     * @return
     * @throws YodaException if input format is invalid.
     */
    public String run() throws YodaException {
        if (!checkValidInput()) {
            throw new YodaException("Find... what?");
        }
        String[] splitInput = input.split(" ", 2);
        String keyword = splitInput[1];
        ArrayList<Task> matchingTasks = findTasks(keyword);

        StringBuilder message = new StringBuilder();
        message.append("Matching tasks:\n");

        for (int i = 0; i < matchingTasks.size(); i++) {
            message.append(i + 1).append(". ").append(matchingTasks.get(i)).append("\n");
        }

        return message.toString();
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
