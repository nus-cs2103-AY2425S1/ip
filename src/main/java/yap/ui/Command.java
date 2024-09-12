package yap.ui;

import yap.task.Task;
import yap.task.TaskList;

/**
 * Class for processing user input and taking appropriate action
 */
public class Command {
    private static final String SEPARATOR = "_____________________________________";
    private TaskList taskList;
    public Command(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Processes a mark command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public void mark(String userInput) throws InputException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.markTask(taskIndex);
            System.out.println(SEPARATOR);
        } catch (IndexOutOfBoundsException | NumberFormatException exception) {
            throw new InputException("You did not provide a valid task index to mark!");
        }
    }

    /**
     * Processes a unmark command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public void unmark(String userInput) throws InputException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.unmarkTask(taskIndex);
            System.out.println(SEPARATOR);
        } catch (IndexOutOfBoundsException | NumberFormatException exception) {
            throw new InputException("You did not provide a valid task index to mark!");
        }
    }

    /**
     * Processes a delete command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public void delete(String userInput) throws InputException {
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            taskList.deleteTask(taskIndex);
            System.out.println(SEPARATOR);
        } catch (IndexOutOfBoundsException | NumberFormatException exception) {
            throw new InputException("You did not provide a valid task index to mark!");
        }
    }

    /**
     * Processes a todo command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public void todo(String userInput) throws InputException {
        Task task = Parser.parseInputAsToDo(userInput);
        taskList.addTask(task);
        System.out.println(SEPARATOR);
    }

    /**
     * Processes a deadline command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public void deadline(String userInput) throws InputException {
        Task task = Parser.parseInputAsDeadline(userInput);
        taskList.addTask(task);
        System.out.println(SEPARATOR);
    }

    /**
     * Processes an event command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public void event(String userInput) throws InputException {
        Task task = Parser.parseInputAsEvent(userInput);
        taskList.addTask(task);
        System.out.println(SEPARATOR);
    }

    /**
     * Processes a list command
     */
    public void list() {
        taskList.listTasks();
        System.out.println(SEPARATOR);
    }

    /**
     * Processes a find command.
     *
     * @param userInput The users' string input.
     */
    public void find(String userInput) {
        taskList.listMatchingDescriptionTasks(Parser.getStringFromFindCommand(userInput));
        System.out.println(SEPARATOR);
    }
}
