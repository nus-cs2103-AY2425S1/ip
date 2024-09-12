package cloudy;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * The Parser class is responsible for interpreting user input and converting it into executable
 * commands for the Cloudy program.
 */
public class Parser {

    private static final String DATE_PATTERN = "^\\d{2}/\\d{2}/\\d{4}$";

    /**
     * Parses the user input into a Command object.
     * @param userInput The user's input as a string.
     * @return A Command object representing the user's intended action.
     */
    public Command parseCommand(String userInput) {
        if (userInput.startsWith("mark ")) {
            return parseMarkCommand(userInput);
        } else if (userInput.startsWith("unmark ")) {
            return parseUnmarkCommand(userInput);
        } else if (userInput.startsWith("todo")) {
            return parseTodoCommand(userInput);
        } else if (userInput.startsWith("deadline")) {
            return parseDeadlineCommand(userInput);
        } else if (userInput.startsWith("event")) {
            return parseEventCommand(userInput);
        } else if (userInput.startsWith("delete")) {
            return parseDeleteCommand(userInput);
        } else if (userInput.startsWith("find")) {
            return parseFindCommand(userInput);
        } else if (userInput.equals("list")) {
            return new Command("list");
        } else if (userInput.equals("bye")) {
            return new Command("bye");
        } else {
            return new Command("invalid");
        }
    }

    /**
     * Parses a mark command from the user's input.
     * @param userInput The user's input containing the mark command.
     * @return A command object for marking a task as complete.
     */
    private Command parseMarkCommand(String userInput) {
        String[] parts = userInput.split(" ");

        if (parts.length < 2) {
            return new Command("invalidTaskFormat");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new Command("mark", taskNumber);
        } catch (NumberFormatException e) {
            return new Command("invalidTaskNum");
        }

    }

    /**
     * Parses an unmark command from the user's input.
     * @param userInput The user's input containing the mark command.
     * @return A command object for marking a task as complete.
     */
    private Command parseUnmarkCommand(String userInput) {
        String[] parts = userInput.split(" ");

        if (parts.length != 2) {
            return new Command("invalidCommand");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new Command("unmark", taskNumber);
        } catch (NumberFormatException e) {
            return new Command("invalidTaskNum");
        }

    }

    private Command parseTodoCommand(String userInput) {
        if (userInput.trim().length() <= 4) {
            return new Command("invalidCommand");
        } else {
            String taskDescription = userInput.substring(5).trim();
            return new Command("todo", taskDescription);
        }
    }

    private Command parseDeadlineCommand(String userInput) {
        if (userInput.length() < 9 || !userInput.contains("/by")) {
            return new Command("invalidDeadline");
        }

        String[] parts = userInput.split("/by ");
        String taskDescription = parts[0].substring(9).trim();
        String inputDeadline = parts.length > 1 ? parts[1].trim() : "";

        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcher = pattern.matcher(inputDeadline);

        if (!matcher.matches()) {
            return new Command("invalidDeadline");
        }

        try {
            String[] deadlineParts = inputDeadline.split("/");
            LocalDate deadline = LocalDate.parse(
                    deadlineParts[2] + "-" + deadlineParts[1] + "-" + deadlineParts[0]);
            return new Command("deadline", taskDescription, deadline);
        } catch (DateTimeParseException e) {
            return new Command("invalidDeadline");
        }

    }

    private Command parseEventCommand(String userInput) {
        if (userInput.length() < 6 || !userInput.contains("/from")) {
            return new Command("invalidEvent");
        }

        String[] partsFrom = userInput.split("/from");
        String taskDescription = partsFrom[0].substring(6).trim();
        String startTime = "", endTime = "";
        if (partsFrom.length > 1) {
            String[] partsTo = partsFrom[1].split("/to");
            startTime = partsTo[0].trim();
            if (partsTo.length > 1) {
                endTime = partsTo[1].trim();
            }
        }

        Pattern pattern = Pattern.compile(DATE_PATTERN);
        Matcher matcherStartTime = pattern.matcher(startTime);
        Matcher matcherEndTime = pattern.matcher(endTime);

        if (!matcherStartTime.matches() || !matcherEndTime.matches()) {
            return new Command("invalidEvent");
        }

        try {
            String[] startTimeParts = startTime.split("/");
            String[] endTimeParts = endTime.split("/");
            LocalDate startTimeFinal = LocalDate.parse(
                    startTimeParts[2] + "-" + startTimeParts[1] + "-" + startTimeParts[0]);
            LocalDate endTimeFinal = LocalDate.parse(
                    endTimeParts[2] + "-" + endTimeParts[1] + "-" + endTimeParts[0]);
            return new Command("event", taskDescription, startTimeFinal, endTimeFinal);
        } catch (DateTimeParseException e) {
            return new Command("invalidEvent");
        }
    }

    private Command parseDeleteCommand(String userInput) {
        String[] parts = userInput.split(" ");

        if (parts.length != 2) {
            return new Command("invalidCommand");
        }

        try {
            int taskNumber = Integer.parseInt(parts[1]);
            return new Command("delete", taskNumber);
        } catch (NumberFormatException e) {
            return new Command("invalidTaskNum");
        }
    }

    private Command parseFindCommand(String userInput) {
        String searchQuery = userInput.substring(5).trim();
        return new Command("find", searchQuery);
    }

    public static Task parseTaskFromFile(String line) {
        String[] parts = line.split(" \\| ");
        if (parts.length < 3) {
            return null;
        }
        String typeOfTask = parts[0];
        boolean isMarked = parts[1].equals("1");
        String taskDescription = parts[2];

        switch (typeOfTask) {
        case "T":
            return new Todo(taskDescription, isMarked);
        case "D":
            LocalDate deadline = LocalDate.parse(parts[3]);
            return new Deadline(taskDescription, deadline, isMarked);
        case "E":
            LocalDate startTime = LocalDate.parse(parts[3]);
            LocalDate endTime = LocalDate.parse(parts[4]);
            return new Event(taskDescription, startTime, endTime, isMarked);
        default:
            return null;
        }
    }


}
