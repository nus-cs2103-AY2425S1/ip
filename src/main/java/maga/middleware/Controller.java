package maga.middleware;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

import maga.commands.Command;
import maga.exceptions.InvalidCommandException;
import maga.task.TaskList;
import maga.task.TaskManager;


/**
 * The {@code Controller} class is responsible for handling user input, delegating commands to the {@code TaskList},
 * and returns the message string back to the {@code Ui} that instantiated the controller.
 * It interacts with the {@code Parser} to convert user input into commands, which are then executed on the task list.
 * The controller also manages input validation and handles exceptions such as date parsing and invalid commands.
 */
public class Controller {
    private final TaskList taskList;
    private final TaskManager taskManager;
    private final Parser parser = new Parser();

    /**
     * Constructs a {@code Controller} with the specified {@code Scanner} and {@code TaskList}.
     * The {@code Controller} initializes the parser and handles user commands based on input.
     *
     * @param taskList The {@code TaskList} that the controller will manage.
     */
    public Controller(TaskList taskList, TaskManager taskManager) {
        this.taskList = taskList;
        this.taskManager = taskManager;
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

    private String tagTask(Command<Integer> command) {
        return taskList.tagTask(command);
    }

    private String createTask(Command<LocalDate[]> command) {
        return taskList.addTask(command);
    }

    /**
     * Casts {@code command<?>} into {@code command<T>} to abstract out {@code SuppressWarnings("unchecked)}.
     * Commands are safe to cast since the Parser class passes in the correct generic type.
     *
     * @param command The command to be casted.
     */
    private <T> Command<T> cast(Command<?> command) {
        @SuppressWarnings("unchecked")
        Command<T> castedCommand = (Command<T>) command;
        return castedCommand;
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
            String temp;
            switch (command.getCommandType()) {
            case "list" -> {
                temp = listTasks(cast(command));
            }
            case "mark" -> {
                temp = markTask(cast(command));
            }
            case "unmark" -> {
                temp = unmarkTask(cast(command));
            }
            case "delete" -> {
                temp = deleteTask(cast(command));
            }
            case "find" -> {
                temp = findTask(cast(command));
            }
            case "todo", "event", "deadline" -> {
                temp = createTask(cast(command));
            }
            case "tag" -> {
                temp = tagTask(cast(command));
            }
            case "bye" -> {
                // no need to save tasks since it is handled in Maga.closeBot() method
                return "Yeah I'ma see you in my next RALLY! A vote for me is a vote for America!";
            }
            default -> throw new InvalidCommandException();
            }
            taskManager.saveTasks(taskList);
            return temp;
        } catch (DateTimeParseException e) {
            return "Error while parsing date - format in yyyy-MM-dd";
        } catch (NumberFormatException e) {
            return "Invalid task number!";
        } catch (InvalidCommandException e) {
            return "HEY! SLEEPY JOE and CROOKED KAMALA "
                    + "might be demented but you're not! Specify a command!";
        }
    }
}
