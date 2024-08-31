package parser;

import commands.*;
import exceptions.InvalidDateException;
import exceptions.InvalidTaskException;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Parser {

    /**
     * Converts stored data input into a user input format that can be processed by the system.
     * The data input format is typically used for loading tasks from storage, and this method converts it into
     * a format that matches user input, including task type and associated details.
     *
     * @param data The string containing the stored task data in a specific format.
     * @return The string representing the user input format based on the stored data.
     */
    public static String dataInputToUserInput(String data) throws InvalidDateException {
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

            int fromIndex = data.indexOf("(from");
            int toIndex = data.indexOf("to:");
            int lastIndex = data.indexOf(")");
            String fromDate = data.substring(fromIndex + 6, toIndex - 1);
            fromDate = Parser.convertDateFormat(fromDate, "MMM dd yyyy", "dd/MM/yyyy");
            String toDate = data.substring(toIndex + 4, lastIndex);
            toDate = Parser.convertDateFormat(toDate, "MMM dd yyyy", "dd/MM/yyyy");
            return "event " + description + " /from " + fromDate + " /to " + toDate;
        default:
            int descriptionEndIndex = data.indexOf("(");
            description = data.substring(descriptionStartIndex, descriptionEndIndex).trim();
            int deadlineIndex = data.indexOf("(by");
            String date = data.substring(deadlineIndex + 4, data.indexOf(")"));
            String inputDateFormat = Parser.convertDateFormat(date.trim(), "MMM dd yyyy", "dd/MM/yyyy");
            return "deadline " + description + " /by " + inputDateFormat;
        }
    }

    public static String convertDateFormat(String dateStr, String sourceFormat, String resultFormat)
            throws InvalidDateException {
        try {
            SimpleDateFormat sourceDateFormat = new SimpleDateFormat(sourceFormat);
            Date date = sourceDateFormat.parse(dateStr);
            SimpleDateFormat resultDateFormat = new SimpleDateFormat(resultFormat);
            return resultDateFormat.format(date);
        } catch (ParseException e) {
           throw new InvalidDateException();
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
       case "find":
           return new FindCommand(userInput.substring(5));
       case "todo", "deadline", "event":
           return new AddTaskCommand(userInput);
       default:
           throw new InvalidTaskException();
       }
    }

    public static String formatInput(String input) {
        input = input.substring(input.indexOf("["));
        char taskType = input.charAt(1);
        int descriptionStartIndex = input.indexOf("] ", input.indexOf("]") + 1) + 2;
        String description;
        String timeDetails;
        switch (taskType) {
            case 'T':
                return "todo " + input.substring(descriptionStartIndex);
            case 'E':
                int endIndex = input.indexOf("(");
                description = input.substring(descriptionStartIndex, endIndex).trim();

                int timeIndex = input.indexOf("(from");
                timeDetails = input.substring(timeIndex).trim();
                timeDetails = timeDetails.replace("(from:", "/from");
                timeDetails = timeDetails.replace("to:", "/to");
                timeDetails = timeDetails.replace(")", "");
                return "event " + description + " " + timeDetails;
            default:
                int descriptionEndIndex = input.indexOf("(");
                description = input.substring(descriptionStartIndex, descriptionEndIndex).trim();
                int deadlineIndex = input.indexOf("(by");
                timeDetails = input.substring(deadlineIndex);
                timeDetails = timeDetails.replace("(by", "/by");
                timeDetails = timeDetails.replace(")", "");
                return "deadline " + description + " " + timeDetails;
        }
    }

}
















