package maga;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

import maga.task.TaskList;


/**
 * The {@code Controller} class is responsible for handling user input, delegating commands to the {@code TaskList},
 * and returns the message string back to the {@code Ui} that instantiated the controller.
 * It interacts with the {@code Parser} to convert user input into commands, which are then executed on the task list.
 * The controller also manages input validation and handles exceptions such as date parsing and invalid commands.
 */
public class Controller {
    private final Scanner scanner;
    private final TaskList taskList;
    private final Parser parser = new Parser();

    /**
     * Constructs a {@code Controller} with the specified {@code Scanner} and {@code TaskList}.
     * The {@code Controller} initializes the parser and handles user commands based on input.
     *
     * @param scanner  The {@code Scanner} object used to read user input.
     * @param taskList The {@code TaskList} that the controller will manage.
     */
    public Controller(Scanner scanner, TaskList taskList) {
        this.scanner = scanner;
        this.taskList = taskList;
    }

    /**
     * Determines whether the user input is the exit command.
     *
     * @param input The input string to check.
     * @return {@code true} if the input is "bye", {@code false} otherwise.
     */
    public boolean isExitCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return true;
        }

        return false;
    }

    private String listTasks(Command<Integer> command) {
        return taskList.listTasks();
    }

    private String markTask(Command<Integer> command) {
        return taskList.markTask(command.getContent());
    }

    private String unmarkTask(Command<Integer> command) {
        return taskList.unmarkTask(command.getContent());
    }

    private String deleteTask(Command<Integer> command) {
        return taskList.deleteTask(command.getContent());
    }

    private String findTask(Command<String> command) {
        return taskList.findTask(command.getContent());
    }

    private String createTask(Command<LocalDate[]> command) {
        return taskList.addTask(command);
    }

    /**
     * Processes user input and delegates the appropriate action based on the parsed command.
     * This method uses the {@code Parser} to interpret the input and execute corresponding commands
     * such as listing, marking, unmarking, deleting, finding, and creating tasks.
     *
     * <p><b>Note:</b> The cast to specific command types uses {@code @SuppressWarnings("unchecked")}
     * because the cast is unchecked. However, it is safe because the {@code Parser} ensures that the
     * correct {@code Command<T>} type is passed for each case.</p>
     *
     * @param input The user's input as a string.
     * @return A response message based on the command executed, or an error message if an exception occurs.
     */
    public String handleInput(String input) {
        try {
            Command<?> command = parser.handleInput(input);
            switch (command.getCommandType()) {
            case "list" -> {
                @SuppressWarnings("unchecked")
                String temp = listTasks((Command<Integer>) command);
                return temp;
            }
            case "mark" -> {
                @SuppressWarnings("unchecked")
                String temp = markTask((Command<Integer>) command);
                return temp;
            }
            case "unmark" -> {
                @SuppressWarnings("unchecked")
                String temp = unmarkTask((Command<Integer>) command);
                return temp;
            }
            case "delete" -> {
                @SuppressWarnings("unchecked")
                String temp = deleteTask((Command<Integer>) command);
                return temp;
            }
            case "find" -> {
                @SuppressWarnings("unchecked")
                String temp = findTask((Command<String>) command);
                return temp;
            }
            case "todo", "event", "deadline" -> {
                @SuppressWarnings("unchecked")
                String temp = createTask((Command<LocalDate[]>) command);
                return temp;
            }
            default -> throw new InvalidCommandException();
            }
        } catch (DateTimeParseException e) {
            return "Error while parsing date - format in yyyy-MM-dd";
        } catch (NumberFormatException e) {
            return "You can only delete a maga.task number! No one calls amendments by their names!!";
        } catch (InvalidCommandException e) {
            return "HEY! SLEEPY JOE and CROOKED KAMALA "
                    + "might be demented but you're not! Specify a command!";
        }
    }
}
