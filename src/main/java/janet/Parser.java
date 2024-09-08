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
        checkInaccurateCommand(commandDetails, numOfTasksInList);
    }


    /**
     * Throws janet.JanetException when,
     * 1. command is empty (eg. ' ') OR command is an enum CommandType value.
     * 2. task description is not provided.
     * 3. task number is not in proper format, not provided, is negative or equal to 0.
     * 4. task number is larger than the number of tasks in the list.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @throws JanetException If command is invalid, task number is not properly specified, task description omitted.
     */
    public static void checkInaccurateCommand(String[] commandDetails, int numOfTasksInlist) throws JanetException {
        if (emptyCommand(commandDetails) || unknownCommand(commandDetails)) {
            // when the command is gibberish OR is an empty command
            throw new JanetException("WHOOPS! I'm only a chatbot, so I don't know what that means...");
        } else if (notByeOrListCommand(commandDetails)) {
            if (taskDescriptionOmitted(commandDetails)) {
                // task description is not provided
                throw new JanetException("WHOOPS! You can't leave out the task's description!");
            } else if (taskNumberInvalidOrAbsent(commandDetails) || taskNumberNegativeOrZero(commandDetails)) {
                // for marking (mark/unmark) and delete commands,
                // task number is either invalid, absent, negative, or less than or equal to 0
                throw new JanetException("WHOOPS! Ensure task number is present and valid!");
            } else if (taskNumberOutOfRange(commandDetails, numOfTasksInlist)) {
                // for marking (mark/unmark) and delete commands, task number is larger than number of tasks in list
                throw new JanetException("WHOOPS! You don't have a task of this number!");
            }
        }
    }


    /**
     * Returns true if the command is empty, just whitespaces (eg. '    ').
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value
     */
    private static boolean emptyCommand(String[] commandDetails) {
        return commandDetails.length == 0;
    }


    private static boolean taskDescriptionOmitted(String[] commandDetails) {
        boolean noDescriptionProvided = commandDetails.length == 1;
        return noDescriptionProvided && isTaskCommand(commandDetails);
    }


    /**
     * Returns true if command is,
     * 1. gibberish and not an enum CommandType value (eg. marker, blah blah, events).
     * 2. 'bye ...' where '...' represents additional texts behind the word bye (eg. bye bye).
     * 3. 'list ...' where '...' represents additional texts behind the word list (eg. list something).
     * false otherwise.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    private static boolean unknownCommand(String[] commandDetails) {
        boolean notACommand = !(getCommandTypes().contains(commandDetails[0].toUpperCase()));
        boolean byeWithAdditionalTexts = (commandDetails.length > 1) && (commandDetails[0].equals("bye"));
        boolean listWithAdditionalTexts = (commandDetails.length > 1) && (commandDetails[0].equals("list"));
        return notACommand || byeWithAdditionalTexts || listWithAdditionalTexts;
    }


    /**
     * Returns true if,
     * 1. task number is not provided (eg. mark).
     * 2. task number is invalid, unable to parse into Integer (eg. unmark three).
     * 3. more than 1 task number is provided, separated by whitespace(s) (eg. delete 3 2).
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    private static boolean taskNumberInvalidOrAbsent(String[] commandDetails) {
        boolean taskNumberAbsent = (commandDetails.length == 1);
        boolean taskNumberInvalid = false;
        if (commandDetails.length == 2 && isMarkingOrDeleteCommand(commandDetails)) {
            // task number is present, check for validity
            try {
                Integer.parseInt(commandDetails[1]);
            } catch (NumberFormatException e) {
                taskNumberInvalid = true;
            }
        } else if (commandDetails.length > 2  && isMarkingOrDeleteCommand(commandDetails)) {
            // more than 1 task number is provided, separated by white space(s).
            taskNumberInvalid = true;
        }
        return taskNumberAbsent || taskNumberInvalid;  // false
    }


    /**
     * Returns true if the command is neither 'bye' nor 'list'.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    private static boolean notByeOrListCommand(String[] commandDetails) {
        // returns true if the command is not 'bye' and 'list', false otherwise.
        return !(commandDetails[0].equals("bye") || commandDetails[0].equals("list"));
    }


    /**
     * Returns true if the task number provided is greater than the number of tasks in list,
     * false otherwise.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @param numOfTasksInList The current total number of tasks in the list.
     * @return A boolean value.
     */
    private static boolean taskNumberOutOfRange(String[] commandDetails, int numOfTasksInList) {
        // returns true if the task number provided is greater than the number of tasks in list, false otherwise
        return isMarkingOrDeleteCommand(commandDetails)
                && Integer.parseInt(commandDetails[1]) > numOfTasksInList;
    }


    /**
     * Returns true if the task number provided is less than or equal to 0,
     * false otherwise.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    private static boolean taskNumberNegativeOrZero(String[] commandDetails) {
        // returns true if the task number provided is less than or equal to 0, false otherwise.
        return isMarkingOrDeleteCommand(commandDetails) && Integer.parseInt(commandDetails[1]) <= 0;
    }


    /**
     * Returns true if command is mark, unmark or delete,
     * false otherwise.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    private static boolean isMarkingOrDeleteCommand(String[] commandDetails) {
        return commandDetails[0].equals("mark")
                || commandDetails[0].equals("unmark")
                || commandDetails[0].equals("delete");
    }


    /**
     * Returns true if command is todo, deadline or event,
     * false otherwise.
     *
     * @param commandDetails A String[], where each element corresponds to a word of the user input.
     * @return A boolean value.
     */
    private static boolean isTaskCommand(String[] commandDetails) {
        return commandDetails[0].equals("todo")
                || commandDetails[0].equals("deadline")
                || commandDetails[0].equals("event");
    }
}
