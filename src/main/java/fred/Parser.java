package fred;

import fred.Exceptions.*;

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
    Action parseInput(String input) throws FredException {
        input = input.strip();
        String[] inputParts = input.split(" ", 2);
        Action action;
        Command command = getCommand(inputParts[0]);
        Command[] commandsWithTaskNumber = new Command[]{Command.MARK_TASK, Command.UNMARK_TASK, Command.DELETE_TASK};
        Command[] commandsWithTaskTypeAndDetails = new Command[]{Command.ADD_TODO_TASK, Command.ADD_DEADLINE_TASK, Command.ADD_EVENT_TASK};
        Command[] commandsWithKeyword = new Command[]{Command.FIND_TASK};
        boolean actionNeedsTaskNumber = Arrays.asList(commandsWithTaskNumber).contains(command);
        boolean actionNeedsTaskTypeAndDetails = Arrays.asList(commandsWithTaskTypeAndDetails).contains(command);
        boolean actionNeedsKeyword = Arrays.asList(commandsWithKeyword).contains(command);
        if (actionNeedsTaskNumber) {
            int taskNumber = getTaskNumber(inputParts[1]);
            action = new Action(command, taskNumber);
        } else if (actionNeedsTaskTypeAndDetails) {
            String taskDetails = getTaskDetails(inputParts[1]);
            TaskType tasktype = getTaskType(command);
            action = new Action(command, tasktype, taskDetails);
        } else if (actionNeedsKeyword) {
            String keyword = getKeyword(inputParts[1]);
            action = new Action(command, keyword);
        } else {
            action = new Action(command);
        }
        System.out.println(action.getCommand());
        return action;
    }

    Command getCommand(String inputPart) throws UnknownCommandException {
        Command[] commands = Command.values();
        for (Command command : commands) {
            if (inputPart.equals(command.getCommandName())) {
                return command;
            }
        }
        throw new UnknownCommandException();
    }

    int getTaskNumber(String inputPart) throws InvalidTaskNumberException {
        try {
            int taskNumber = Integer.parseInt(inputPart) - 1;
            return taskNumber;
        } catch (NumberFormatException e) {
            throw new InvalidTaskNumberException();
        }
    }

    String getTaskDetails(String inputPart) throws EmptyTaskDescriptionException {
        if (inputPart.isEmpty()) {
            throw new EmptyTaskDescriptionException();
        }
        return inputPart;
    }

    String getKeyword(String inputPart) {
        return inputPart;
    }

    TaskType getTaskType(Command command) {
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
}

