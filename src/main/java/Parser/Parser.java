package Parser;

import Commands.*;
import exceptions.InvalidTaskException;

/**
 * Utility class that provides methods to parse input data and convert it into commands or user input formats.
 * This class is responsible for interpreting both stored data and user commands.
 */
public class Parser {

    /**
     * Converts stored data input into a user input format that can be processed by the system.
     * The data input format is typically used for loading tasks from storage, and this method converts it into
     * a format that matches user input, including task type and associated details.
     *
     * @param data The string containing the stored task data in a specific format.
     * @return The string representing the user input format based on the stored data.
     */
    public static String dataInputToUserInput(String data) {
        data = data.substring(data.indexOf("["));
        char taskType = data.charAt(1);
        int descriptionStartIndex = data.indexOf("] ", data.indexOf("]") + 1) + 2;
        String description;
        String timeDetails;
        switch (taskType) {
        case 'T':
            return "todo " + data.substring(descriptionStartIndex);
        case 'E':
            int endIndex = data.indexOf("(");
            description = data.substring(descriptionStartIndex, endIndex).trim();

            int timeIndex = data.indexOf("(from");
            timeDetails = data.substring(timeIndex).trim();
            timeDetails = timeDetails.replace("(from:", "/from");
            timeDetails = timeDetails.replace("to:", "/to");
            timeDetails = timeDetails.replace(")", "");
            return "event " + description + " " + timeDetails;
        default:
            int descriptionEndIndex = data.indexOf("(");
            description = data.substring(descriptionStartIndex, descriptionEndIndex).trim();
            int deadlineIndex = data.indexOf("(by");
            timeDetails = data.substring(deadlineIndex);
            timeDetails = timeDetails.replace("(by", "/by");
            timeDetails = timeDetails.replace(")", "");
            return "deadline " + description + " " + timeDetails;
        }
    }

    /**
     * Converts user input into a corresponding command object that can be executed.
     * This method interprets the user's input string and determines the appropriate command
     * to return, based on the input. The method supports various commands including "bye", "list",
     * "delete", "mark", "unmark", and task creation commands ("todo", "deadline", "event").
     *
     * @param userInput The string containing the user's command input.
     * @return A Command object representing the user's intended action.
     * @throws InvalidTaskException If the user input does not match any valid command or task type.
     * @throws ArrayIndexOutOfBoundsException If the user input is missing required arguments.
     */
    public static Command inputToCommand(String userInput) throws  InvalidTaskException, ArrayIndexOutOfBoundsException {
       String strippedInput= userInput.toLowerCase().trim();
       if (strippedInput.isEmpty()) {
           return null;
       }

       String[] words = strippedInput.split(" ");
       switch (words[0]) {
       case "bye":
           return new ExitCommand();
       case "list":
           return new ListCommand();
       case "delete":
           return new DeleteCommand(Integer.parseInt(words[1]) - 1);
       case "mark":
           return new MarkCommand(Integer.parseInt(words[1]) - 1);
       case "unmark":
           return new UnmarkCommand(Integer.parseInt(words[1]) - 1);
       case "todo", "deadline", "event":
           return new AddTaskCommand(userInput);
       default:
           throw new InvalidTaskException();
       }
    }
}
















