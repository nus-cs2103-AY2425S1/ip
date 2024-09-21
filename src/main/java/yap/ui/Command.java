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
        assert userInput.startsWith("mark") : "Input should start with mark";
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
        assert userInput.startsWith("unmark") : "Input should start with unmark";
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
        assert userInput.startsWith("delete") : "Input should start with delete";
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
        assert userInput.startsWith("todo") : "Input should start with todo";
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
        assert userInput.startsWith("deadline") : "Input should start with deadline";
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
        assert userInput.startsWith("event") : "Input should start with event";
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
        assert userInput.startsWith("find") : "Input should start with find";
        taskList.listMatchingDescriptionTasks(Parser.getStringFromFindCommand(userInput));
        System.out.println(SEPARATOR);
    }
}
