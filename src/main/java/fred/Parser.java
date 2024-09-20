package fred;

import fred.Exceptions.EmptyInputException;
import fred.Exceptions.EmptyKeywordsException;
import fred.Exceptions.EmptyTaskDescriptionException;
import fred.Exceptions.FredException;
import fred.Exceptions.InvalidTagException;
import fred.Exceptions.InvalidTaskNumberException;
import fred.Exceptions.UnknownCommandException;
import java.util.Arrays;

/**
 * The Parser class handles the parsing of user input. It converts raw input strings
 * into structured commands that can be executed by the application. The parser
 * identifies and processes various commands, throwing exceptions for invalid inputs.
 */
public class Parser {

    /**
     * Parses the user's input string and returns an array representing the command and its arguments.
     *
     * @param input The raw input string entered by the user.
     * @return A String array where the first element is the command, and subsequent elements are arguments.
     * @throws FredException If the input is invalid or contains an unknown command.
     */
    public Action parseInput(String input) throws FredException {
        input = input.strip();
        if (input.isEmpty()) {
            throw new EmptyInputException();
        }
        Action action;
        String[] inputParts = input.split(" ", 2);
        Command command = getCommand(inputParts[0]);
        Command[] commandsWithTaskTypeAndDetails = new Command[]{Command.ADD_TODO_TASK, Command.ADD_DEADLINE_TASK, Command.ADD_EVENT_TASK};
        Command[] commandsWithTaskNumber = new Command[]{Command.MARK_TASK, Command.UNMARK_TASK, Command.DELETE_TASK};
        if (Arrays.asList(commandsWithTaskTypeAndDetails).contains(command)) {
            action = getAddAction(command, input);
        } else if (Arrays.asList(commandsWithTaskNumber).contains(command)) {
            action = getActionWithTaskNumber(command, input);
        } else if (command == Command.FIND_TASK) {
            action = getFindAction(command, input);
        } else if (command == Command.TAG_TASK) {
            action = getTagAction(command, input);
        } else {
            action = new Action(command);
        }
        return action;
    }

    private Command getCommand(String inputPart) throws UnknownCommandException {
        Command[] commands = Command.values();
        for (Command command : commands) {
            if (inputPart.equals(command.getCommandName())) {
                return command;
            }
        }
        throw new UnknownCommandException();
    }

    private int getTaskNumber(String inputPart) throws InvalidTaskNumberException {
        try {
            int taskNumber = Integer.parseInt(inputPart) - 1;
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    private String getTaskDetails(String inputPart) throws EmptyTaskDescriptionException {
        if (inputPart.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        return inputPart;
    }

    private String getKeyword(String inputPart) throws EmptyKeywordsException {
        if (inputPart.isEmpty()) {
            throw new EmptyKeywordsException();
        }
        return inputPart;
    }

    private TaskType getTaskType(Command command) {
        switch (command) {
            case ADD_TODO_TASK:
                return TaskType.TODO;
            case ADD_DEADLINE_TASK:
                return TaskType.DEADLINE;
            case ADD_EVENT_TASK:
                return TaskType.EVENT;
            default:
                return null;
        }
    }

    private String getTag(String inputPart) throws InvalidTagException {
        if (inputPart.isEmpty()) {
            throw new InvalidTagException();
        }
        return inputPart;
    }

    private Action getAddAction(Command command, String input) throws FredException {
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length < 2) {
            throw new EmptyTaskDescriptionException();
        }
        TaskType taskType = getTaskType(command);
        String taskDetails = getTaskDetails(inputParts[1]);
        return new Action(command, taskType, taskDetails);
    }

    private Action getActionWithTaskNumber(Command command, String input) throws FredException {
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length < 2) {
            throw new InvalidTaskNumberException();
        }
        int taskNumber = getTaskNumber(inputParts[1]);
        return new Action(command, taskNumber);
    }

    private Action getFindAction(Command command, String input) throws FredException {
        String[] inputParts = input.split(" ", 2);
        if (inputParts.length < 2) {
            throw new EmptyKeywordsException();
        }
        String keyword = getKeyword(inputParts[1]);
        return new Action(command, keyword);
    }

    private Action getTagAction(Command command, String input) throws FredException {
        String[] inputParts = input.split(" ", 3);
        if (inputParts.length < 3) {
            throw new InvalidTagException();
        }
        int taskNumber = getTaskNumber(inputParts[1]);
        String tag = getTag(inputParts[2]);
        return new Action(command, taskNumber, tag);
    }
}

