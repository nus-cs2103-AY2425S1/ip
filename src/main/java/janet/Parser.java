package janet;

import java.util.ArrayList;
import java.util.Arrays;

public class Parser {

    public static CommandType getCommand(String[] commandDetails) {
        return CommandType.valueOf(commandDetails[0].toUpperCase());
    }

    public static String[] getCommandDetails(String inputLine) {
        return inputLine.split(" ");   // an array containing each word of the command
    }

    public static ArrayList<String> getCommandTypes() {
        ArrayList<String> acceptableCommands = new ArrayList<String>();
        for (CommandType commandType : CommandType.values()) {
            acceptableCommands.add(commandType.name());
        }
        return acceptableCommands;
    }


    public static void checkUserInput(String[] commandDetails, int numOfTasksInList) throws JanetException {
        validateCommand(commandDetails, numOfTasksInList);
        checkInaccurateCommand(commandDetails);
        validateDeadline(commandDetails);
        validateEvent(commandDetails);
        validateByeAndList(commandDetails);
    }


    /**
     * Throws janet.JanetException when,
     * 1. mark/unmark/delete X, where X cannot be parsed into an Integer.
     * 2. mark/unmark/delete X, where X can be parsed into an Integer but, is <= 0 or > number of tasks in list.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
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
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @throws JanetException a custom exception class specific to Janet
     */
    public static void checkInaccurateCommand(String[] commandDetails) throws JanetException {
        // checks for inaccurate commands 1. rubbish, 2. without any task description, 3. no number for mark/unmark/delete.
        if (!getCommandTypes().contains(commandDetails[0].toUpperCase())) {
            // when the command is gibberish and NOT one of the commands in CommandType
            throw new JanetException("WHOOPS! I'm only a chatbot, so I don't know what that means...");
        } else if (commandDetails.length == 1 && !(commandDetails[0].equals("bye") || commandDetails[0].equals("list"))) {
            if (commandDetails[0].equals("mark") || commandDetails[0].equals("unmark") || commandDetails[0].equals("delete")) {
                // when the command is either (mark, unmark, delete), BUT there is no task specified
                throw new JanetException("WHOOPS! I don't know which task you are referring to...");
            } else {
                // when the command is either (todo, deadline, todo), BUT there is no task description
                throw new JanetException("WHOOPS! You can't leave out the task's description!");
            }
        }
    }


    public static boolean byKeywordNotFound(String[] commandDetails) {
        for (String word : commandDetails) {
            if (word.equals("/by")) {
                return false;
            }
        }
        return true;
    }

    public static boolean fromOrToKeywordNotFound(String[] commandDetails) {
        boolean fromNotFound = false;
        boolean toNotFound = false;
        for (String word : commandDetails) {
            if (word.equals("/from")) {
                fromNotFound = true;
            }
            if (word.equals("/to")) {
                toNotFound = true;
            }
        }
        return fromNotFound && toNotFound;
    }


    public static void validateDeadline(String[] commandDetails) throws JanetException {
        if (commandDetails[0].equals("deadline") &&
                commandDetails.length > 1 &&
                byKeywordNotFound(commandDetails)
        ) {
            throw new JanetException("WHOOPS! Missing/Wrong keywords for creating deadline...");
        }
    }

    public static void validateEvent(String[] commandDetails) throws JanetException {
        if (commandDetails[0].equals("event") &&
                commandDetails.length > 1 &&
                !fromOrToKeywordNotFound(commandDetails)
        ) {
            throw new JanetException("WHOOPS! Missing/Wrong keywords for creating event...");
        }
    }

    public static void validateByeAndList(String[] commandDetails) throws JanetException {
        if ((commandDetails[0].equals("bye") || commandDetails[0].equals("list")) &&
                commandDetails.length > 1
        ) {
            throw new JanetException("WHOOPS! Please ensure command is correct!");
        }
    }

}
