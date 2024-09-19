package juno.command;

import java.util.ArrayList;
import java.util.stream.Collectors;

import juno.manager.TaskManager;
import juno.manager.exception.TaskManagerException;
import juno.task.Task;

/**
 * A class to find specific tasks for users based on the keyword the user inputs.
 */
public class FindCommand extends Command {
    private static final String SPLIT_TASK_DELIMITER = "\\s+";
    private static final String NO_TASK_FOUND_STRING = "No matching tasks found for the keyword: ";
    private static final String EMPTY_TASK_LIST_STRING = "\uD83C\uDF31 No tasks added yet! "
            + "Why not plant the first seed? \uD83C\uDF31";
    private static final String INVALID_FIND_TASK_STRING = "\uD83D\uDE15 Hmm, something went wrong. "
            + "Please enter a valid task string after find command. "
            + "(\uD83D\uDCA1 Tip: You can type \"list\" to see your tasks)";
    private static final String MATCHING_TASK_STRING = "Here are the matching task(s) in your list:";
    private ArrayList<Task> tasks;
    private String userInput;

    /**
     * Constructs a FindCommand instance which takes in a user inputted String and a TaskManager instance.
     * Initialises the task list from the TaskManager by calling <code>getTasksArray()</code> method.
     *
     * @param userInput The input provided by the user to search tasks.
     * @param taskManager The TaskManager instance to retrieve the tasks from.
     */
    public FindCommand(String userInput, TaskManager taskManager) {
        this.userInput = userInput;
        this.tasks = taskManager.getTasksArray();
    }

    /**
     * Executes the command to find all tasks based on the user input.
     * If the task list after finding is empty, throws a TaskManagerException.
     * Otherwise, prints each task found in a formatted list and the total
     * number of tasks.
     * Can be executed with the "find {keyword}" input prompt.
     *
     * @throws TaskManagerException If no tasks are present in the list.
     */
    @Override
    public String runCommand() throws TaskManagerException {
        if (this.tasks.isEmpty()) {
            throw new TaskManagerException(EMPTY_TASK_LIST_STRING, TaskManagerException.ErrorType.EMPTY_LIST);
        }
        String taskString;
        try {
            taskString = userInput.split(SPLIT_TASK_DELIMITER, 2)[1];
        } catch (ArrayIndexOutOfBoundsException | NumberFormatException | NullPointerException e) {
            throw new TaskManagerException(INVALID_FIND_TASK_STRING,
                    TaskManagerException.ErrorType.INVALID_FIND_TASK);
        }
        ArrayList<Task> tasksFound = this.findTask(taskString);
        if (tasksFound.isEmpty()) {
            throw new TaskManagerException(NO_TASK_FOUND_STRING + taskString,
                    TaskManagerException.ErrorType.NO_TASK_FOUND);
        }
        StringBuilder outString = new StringBuilder(MATCHING_TASK_STRING);
        for (int i = 0; i < tasksFound.size(); i++) {
            String formmattedString = String.format(
                    "%d. %s", (i + 1),
                    tasksFound.get(i).toString()
            );
            outString.append("\n").append(formmattedString);
        }
        outString.append("\n").append("\uD83C\uDFAF I have found ").append(tasksFound.size())
                .append(" task(s) related to ").append("your prompt.");
        return outString.toString();
    }
    private ArrayList<Task> findTask(String taskString) {
        ArrayList<Task> tasksFound = this.tasks.stream()
                .filter(task -> task.getDescription().toLowerCase().contains(taskString.toLowerCase())
                        || task.getTaskType().toLowerCase().contains(taskString.toLowerCase()))
                .collect(Collectors.toCollection(ArrayList::new));
        return tasksFound;
    }
}
