package fred;

import fred.Exceptions.*;

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
    String[] parseInput(String input) throws FredException {
        input = input.strip();
        String[] inputParts = input.split(" ", 2);

        if (inputParts.length == 1) {
            if (inputParts[0].isEmpty()) {
                throw new EmptyInputException();
            } else if (inputParts[0].equals("bye")) {
                return new String[]{"sayFarewell"};
            } else if (inputParts[0].equals("list")) {
                return new String[]{"printTaskList"};
            } else if (inputParts[0].equals("mark") || inputParts[0].equals("unmark") || inputParts[0].equals("delete")) {
                throw new InvalidTaskNumberException();
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event") || inputParts[0].equals("find")) {
                throw new EmptyTaskDescriptionException();
            } else {
                throw new UnknownCommandException();
            }
        } else if (inputParts.length == 2) {
            inputParts[1] = inputParts[1].strip();
            if (inputParts[0].equals("mark") || inputParts[0].equals("unmark") || inputParts[0].equals("delete")) {
                int taskNumber;
                try {
                    taskNumber = Integer.parseInt(inputParts[1]) - 1;
                } catch (NumberFormatException e) {
                    throw new InvalidTaskNumberException();
                }
                if (inputParts[0].equals("mark")) {
                    return new String[]{"markTaskAsDone", String.valueOf(taskNumber)};
                } else if (inputParts[0].equals("unmark")) {
                    return new String[]{"markTaskAsNotDone", String.valueOf(taskNumber)};
                } else {
                    return new String[]{"deleteFromTaskList", String.valueOf(taskNumber)};
                }
            } else if (inputParts[0].equals("todo") || inputParts[0].equals("deadline") || inputParts[0].equals("event")) {
                if (inputParts[1].isEmpty()) {
                    throw new EmptyTaskDescriptionException();
                }
                return new String[]{"addToTaskList", inputParts[0], inputParts[1]};
            } else if (inputParts[0].equals("find")) {
                if (inputParts[1].isEmpty()) {
                    throw new EmptyTaskDescriptionException();
                }
                return new String[]{"findTaskInTaskList", inputParts[1]};
            }
            else {
                throw new UnknownCommandException();
            }
        }
        return null;
    }
}
