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
    public String mark(String userInput) throws InputException {
        assert userInput.startsWith("mark") : "Input should start with mark";
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            String outputLine = taskList.markTask(taskIndex);
            System.out.println(SEPARATOR);
            return outputLine;
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
    public String unmark(String userInput) throws InputException {
        assert userInput.startsWith("unmark") : "Input should start with unmark";
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            String outputLine = taskList.unmarkTask(taskIndex);
            System.out.println(SEPARATOR);
            return outputLine;
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
    public String delete(String userInput) throws InputException {
        assert userInput.startsWith("delete") : "Input should start with delete";
        try {
            int taskIndex = Integer.parseInt(userInput.split(" ")[1]) - 1;
            String outputLine = taskList.deleteTask(taskIndex);
            System.out.println(SEPARATOR);
            return outputLine;
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
    public String todo(String userInput) throws InputException {
        assert userInput.startsWith("todo") : "Input should start with todo";
        Task task = Parser.parseInputAsToDo(userInput);
        String outputLine = taskList.addTask(task);
        System.out.println(SEPARATOR);
        return outputLine;
    }

    /**
     * Processes a deadline command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public String deadline(String userInput) throws InputException {
        assert userInput.startsWith("deadline") : "Input should start with deadline";
        Task task = Parser.parseInputAsDeadline(userInput);
        String outputLine = taskList.addTask(task);
        System.out.println(SEPARATOR);
        return outputLine;
    }

    /**
     * Processes an event command.
     *
     * @param userInput The users' string input.
     * @throws InputException if the input is invalid and cannot be interpreted.
     */
    public String event(String userInput) throws InputException {
        assert userInput.startsWith("event") : "Input should start with event";
        Task task = Parser.parseInputAsEvent(userInput);
        String outputLine = taskList.addTask(task);
        System.out.println(SEPARATOR);
        return outputLine;
    }

    public String fixedduration(String userInput) throws InputException {
        assert userInput.startsWith("fixedduration");
        Task task = Parser.parseInputAsFixedDurationTask(userInput);
        String outputLine = taskList.addTask(task);
        System.out.println(SEPARATOR);
        return outputLine;
    }

    /**
     * Processes a list command
     */
    public String list() {
        String outputLine = taskList.listTasks();
        System.out.println(SEPARATOR);
        return outputLine;
    }

    /**
     * Processes a find command.
     *
     * @param userInput The users' string input.
     * @return The message output by the find command.
     */
    public String find(String userInput) {
        assert userInput.startsWith("find") : "Input should start with find";
        String outputLine = taskList.listMatchingDescriptionTasks(Parser.getStringFromFindCommand(userInput));
        System.out.println(SEPARATOR);
        return outputLine;
    }

    public String help() {
        return "Command List:\n"
                + "todo task_description - creates a todo task.\n"
                + "deadline task_description /by yyyy-mm-dd - creates a task with a deadline.\n"
                + "event task_description /from yyyy-mm-dd /to yyyy-mm-dd - creates an event"
                + "that starts and ends at a specific time.\n"
                + "fixedduration task_description /duration duration_in_hours"
                + " - creates a task with a fixed duration in hours.\n"
                + "list - lists all the tasks stored currently.\n"
                + "find task_description - finds a task that matches the task_description.\n"
                + "mark task_number - marks the task as completed.\n"
                + "unmark task_number - marks the task as uncompleted.\n"
                + "delete task_number - deletes the task from the list.\n";
    }

}
