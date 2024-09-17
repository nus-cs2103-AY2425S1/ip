package parser;

import socchat.SocchatException;
import socchat.TaskList;
import socchat.task.Task;

import static socchat.Socchat.exit;

/**
 * The {@code Parser} class is responsible for interpreting user commands and executing
 * the corresponding actions on the provided {@link TaskList}. It handles string tokenization,
 * command parsing, and exception handling.
 */
public class Parser {
    private final TaskList taskList;

    public Parser(TaskList taskList) {
        this.taskList = taskList;
    }

    /**
     * Enum representing all available commands in the Socchat application.
     */
    enum Command {
        BYE, LIST, MARK, UNMARK, TODO, EVENT, DEADLINE, DELETE, FIND
    }

    /**
     * Converts the input string to a Command enum value.
     * Throws an exception if the input does not match any command.
     *
     * @param input the command string input from the user
     * @return the corresponding Command enum value
     * @throws SocchatException if the input does not match a valid command
     */
    public static Command getCommand(String input) throws SocchatException {
        try {
            return Command.valueOf(input.toUpperCase());
        } catch (IllegalArgumentException e) {
            throw new SocchatException("Uh Ohh! Socchat does not understand this...");
        }

    }

    /**
     * Tokenizes the user input string into command and argument parts.
     * If the input contains no spaces, the second part of the result will be an empty string.
     *
     * @param input the full input command string
     * @return a string array where the first element is the command and the second is the remaining input
     */
    public static String[] extractCommand(String input) {
        int firstSpaceIndex = input.indexOf(' ');
        String[] commandTokens = new String[2];

        if (firstSpaceIndex != -1) {
            String command = input.substring(0, firstSpaceIndex);
            commandTokens[0] = command;
            String remaining = input.substring(firstSpaceIndex + 1);
            commandTokens[1] = remaining.trim();
        } else {
            commandTokens[0] = input;
            commandTokens[1] = "";
        }
        return commandTokens;
    }


    /**
     * Gets the corresponding response for each command.
     *
     * @param input the input tokens that have been processed in {@link #extractCommand(String)} processInput
     * @return the generated response as a String.
     */
    public String getResponse(String... input) {
        assert input.length == 2;
        String commandInput = input[0];
        String remaining = input[1];
        try {
            Command command = getCommand(commandInput);
            switch (command) {
            case BYE:
                return exit();
            case LIST:
                return taskList.list();
            case MARK:
                return taskList.setMark(remaining, true);
            case UNMARK:
                return  taskList.setMark(remaining, false);
            case TODO:
                return handleTodoCommand(remaining);
            case DEADLINE:
                return handleDeadlineCommand(remaining);
            case EVENT:
                return handleEventCommand(remaining);
            case DELETE:
                return taskList.delete(remaining);
            case FIND:
                return taskList.find(remaining);
            default:
                return "Unrecognized command. Please try again.";
            }

        } catch (SocchatException e) {
            return e.getMessage();
        }
    }
    /**
     * Handles the "TODO" command by parsing the task and adding it to the task list.
     *
     * @param remaining the remaining input after the "TODO" command
     * @return the response message after adding the task
     * @throws SocchatException if the task cannot be parsed or added
     */
    private String handleTodoCommand(String remaining) throws SocchatException {
        Task t = TaskParser.todoParser(remaining);
        return taskList.addTask(t);
    }

    /**
     * Handles the "DEADLINE" command by parsing the deadline task and adding it to the task list.
     *
     * @param remaining the remaining input after the "DEADLINE" command
     * @return the response message after adding the task
     * @throws SocchatException if the task cannot be parsed or added
     */
    private String handleDeadlineCommand(String remaining) throws SocchatException {
        Task d = TaskParser.deadlineParser(remaining);
        return taskList.addTask(d);
    }

    /**
     * Handles the "EVENT" command by parsing the event task and adding it to the task list.
     *
     * @param remaining the remaining input after the "EVENT" command
     * @return the response message after adding the task
     * @throws SocchatException if the task cannot be parsed or added
     */
    private String handleEventCommand(String remaining) throws SocchatException {
        Task e = TaskParser.eventParser(remaining);
        return taskList.addTask(e);
    }
}

