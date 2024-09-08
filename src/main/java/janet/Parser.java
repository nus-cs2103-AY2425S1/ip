package janet;

import java.util.ArrayList;

/**
 * Represents the way Janet makes sense of user commands.
 */
public class Parser {

    /**
     * Returns the corresponding CommandType based on the String value of commandDetails[0].
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return Enum CommandType value
     */
    public static CommandType getCommand(String[] commandDetails) {
        return CommandType.valueOf(commandDetails[0].toUpperCase());
    }


    /**
     * @param inputLine User's command that was typed into the command line.
     * @return A String array, where each element corresponds to a word of the user input.
     */
    public static String[] getCommandDetails(String inputLine) {
        return inputLine.split(" ");   // an array containing each word of the command
    }


    /**
     * Returns an ArrayList<String>, where each element corresponds
     * to the name of the CommandType enum value.
     *
     * @return An ArrayList<String>, containing the String values of all the CommandType values.
     */
    public static ArrayList<String> getCommandTypes() {
        ArrayList<String> acceptableCommands = new ArrayList<String>();
        for (CommandType commandType : CommandType.values()) {
            acceptableCommands.add(commandType.name());
        }
        return acceptableCommands;
    }


    /**
     * Runs all the checks to validate user input.
     * If the user input is invalid, a JanetException will be thrown.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @param numOfTasksInList Total number of tasks inside the list.
     * @throws JanetException If user input is invalid.
     */
    public static void userInputChecker(String[] commandDetails, int numOfTasksInList) throws JanetException {
        // validateCommand(commandDetails, numOfTasksInList);
        checkInaccurateCommand(commandDetails, numOfTasksInList);
    }


    /**
     * Throws janet.JanetException when,
     * 1. mark/unmark/delete X, where X cannot be parsed into an Integer.
     * 2. mark/unmark/delete X, where X can be parsed into an Integer but, is <= 0 or > number of tasks in list.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @throws JanetException If specified task number is out of bounds/unparsable to Integer.
     */
    public static void validateCommand(String[] commandDetails, int numOfTasksInList) throws JanetException {
        // when mark/unmark/delete X, where X is too big (out or bounds) OR <= 0 OR when the list is empty.
        if (commandDetails.length == 0) {
            throw new JanetException("Please do not leave any empty lines!");
        }
        if ((commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") ||
                commandDetails[0].equals("delete")) && commandDetails.length > 1) {
            // when the command is mark/unmark X OR delete, where X is an invalid num (too big or <= 0)
            int taskNumber;
            assert commandDetails.length == 2;
            try {
                taskNumber = Integer.parseInt(commandDetails[1]);   // commandDetails[1] could be a string
            } catch (NumberFormatException e) {
                // for eg. mark/unmark/delete SOMETHING, where SOMETHING cannot be parsed into an Integer
                throw new JanetException("WHOOPS! Please provide an integer value task number!");
            }
            if (taskNumber <= 0) {
                throw new JanetException("WHOOPS! Your task number cannot be negative or 0!");
            } else if (taskNumber > numOfTasksInList) {
                throw new JanetException("WHOOPS! You don't have a task of this number!");
            }
        }
    }


    /**
     * Throws janet.JanetException when,
     * 1. first word in command is not todo/event/deadline/mark/unmark/delete.
     * 2. mark/unmark/delete and the task number is not specified.
     * 3. todo/event/deadline and the description is not stated.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @throws JanetException If task number is not specified, task description is omitted, or unknown command.
     */
    public static void checkInaccurateCommand(String[] commandDetails, int numOfTasksInlist) throws JanetException {
        // checks for inaccurate commands 1. rubbish, 2. without any task description, 3. no number for mark/unmark/delete.
        if (unknownCommand(commandDetails)) {
            // when the command is gibberish and NOT one of the commands in CommandType
            throw new JanetException("WHOOPS! I'm only a chatbot, so I don't know what that means...");
        } else if (notByeOrListCommand(commandDetails)) {
            if (taskNumberInvalidOrAbsent(commandDetails) || taskNumberNegativeOrZero(commandDetails)) {
                // when the command is either (mark, unmark, delete), BUT task num not specified or invalid format
                throw new JanetException("WHOOPS! Ensure task number is present and valid!");
            } else if (taskNumberOutOfRange(commandDetails, numOfTasksInlist)) {
                throw new JanetException("WHOOPS! You don't have a task of this number!");
            } else if (taskDescriptionOmitted(commandDetails)) {
                // when the command is either (todo, deadline, todo), BUT there is no task description
                throw new JanetException("WHOOPS! You can't leave out the task's description!");
            }
        }
    }


    public static boolean emptyCommand(String[] commandDetails) {
        return commandDetails.length == 0;
    }

    public static boolean taskDescriptionOmitted(String[] commandDetails) {
        return false;
    }


    /**
     * Returns true if command is,
     * 1. gibberish and not an enum CommandType value (eg. marker, blah blah, events).
     * 2. 'bye ...' where '...' represents additional texts behind the word bye.
     * 3. 'list ...' where '...' represents additional texts behind the word list.
     * false otherwise.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    public static boolean unknownCommand(String[] commandDetails) {
        boolean notACommand = !(getCommandTypes().contains(commandDetails[0].toUpperCase()));
        boolean byeWithAdditionalTexts = (commandDetails.length > 1) && (commandDetails[0].equals("bye"));
        boolean listWithAdditionalTexts = (commandDetails.length > 1) && (commandDetails[0].equals("list"));

        return notACommand || byeWithAdditionalTexts || listWithAdditionalTexts;
    }


    public static boolean taskNumberInvalidOrAbsent(String[] commandDetails) throws JanetException {
        boolean taskNumberAbsent = (commandDetails.length == 1);
        boolean taskNumberInvalid = false;
        int taskNumber;
        if (commandDetails.length == 2) {
            // task number is present, check for validity
            try {
                taskNumber = Integer.parseInt(commandDetails[1]);
            } catch (NumberFormatException e) {
                // throw new JanetException("WHOOPS! Please provide an integer value task number!");
                taskNumberInvalid = true;
            }
        } else if (commandDetails.length > 2) {
            // more than 1 task number is provided, separated by white space(s).
            taskNumberInvalid = true;
        }
        return taskNumberAbsent || taskNumberInvalid;
    }


    public static boolean notByeOrListCommand(String[] commandDetails) {
        return !(commandDetails[0].equals("bye") || commandDetails[0].equals("list"));
    }


    public static boolean taskNumberOutOfRange(String[] commandDetails, int numOfTasksInList) {
        return Integer.parseInt(commandDetails[1]) > numOfTasksInList;
    }


    public static boolean taskNumberNegativeOrZero(String[] commandDetails) {
        return Integer.parseInt(commandDetails[1]) <= 0;
    }
}
