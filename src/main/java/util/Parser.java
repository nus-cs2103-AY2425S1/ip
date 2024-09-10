package util;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import exceptions.DeadlineException;
import exceptions.EventException;
import exceptions.FindException;
import exceptions.InvalidDateException;
import exceptions.MizzException;
import exceptions.ToDoException;
import tasks.Deadline;
import tasks.Event;
import tasks.Task;
import tasks.ToDo;

/**
 * Utility class to parse data.
 */
public class Parser {
    /**
     * Method to read the items in the Storage class.
     *
     * @param storage The Storage object being used.
     * @return A List of the tasks stored in the Storage.
     * @throws MizzException if entries in the Storage are wrongly formatted.
     */
    public static List<Task> parseFromStorage(Storage storage) throws MizzException {
        assert storage != null : "Storage must not be null";

        List<Task> tasks = new ArrayList<>();
        String[] entries = storage.toArray();

        for (String entry : entries) {
            assert entry != null : "Bad entry: must not be null";

            if (entry.length() < 1 || entry.length() < 7) {
                throw new MizzException("Incomplete entry!:" + entry);
            }

            String[] parts = entry.substring(7).split("\\s+");
            char taskType = entry.charAt(1);
            String details = String.join(" ", parts);
            if (details.isEmpty()) {
                throw new MizzException("Missing details: " + entry);
            }
            Task t;
            switch (taskType) {
            case 'T': {
                t = new ToDo(details);
                break;
            }
            case 'D': {
                int byIdx = details.indexOf(" (by: ");
                if (byIdx == -1) {
                    throw new MizzException("Invalid format for Deadline: " + details);
                }
                String description = details.substring(0, byIdx);
                String by = details.substring(byIdx + 6, details.length() - 1);
                try {
                    LocalDate byDate =
                            LocalDate.parse(by, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    t = new Deadline(description, byDate);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException(e.getMessage());
                }
                break;
            }
            case 'E': {
                int fromIdx = details.indexOf(" (from: ");
                int toIdx = details.indexOf(" to: ");
                if (fromIdx == -1 || toIdx == -1) {
                    throw new MizzException("Invalid format for Event: " + details);
                }
                String description = details.substring(0, fromIdx);
                String from = details.substring(fromIdx + 8, toIdx);
                String to = details.substring(toIdx + 5, details.length() - 1);
                try {
                    LocalDate fromDate =
                            LocalDate.parse(from, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    LocalDate toDate =
                            LocalDate.parse(to, DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    t = new Event(description, fromDate, toDate);
                } catch (DateTimeParseException e) {
                    throw new InvalidDateException(e.getMessage());
                }
                break;
            }
            default:
                throw new MizzException("Invalid entry found in file!: " + taskType);
            }
            assert t != null : "Task must not be null";
            tasks.add(t);

            if (entry.charAt(4) == 'X') {
                t.markDone();
            }
        }

        return tasks;
    }

    /**
     * Utility method to parse and clean the user input.
     *
     * @param inputString The input from the scanner.
     * @return The parsed string as an array of size 4 where:
     *         <ul>
     *         <li>parsedString[0] -> command</li>
     *         <li>parsedString[1] -> description</li>
     *         <li>parsedString[2] -> "" if todo | by if deadline | from if event</li>
     *         <li>parsedString[3] -> to if event else ""</li>
     *         </ul>
     * @throws MizzException if input string is wrongly formatted or missing information.
     */
    public static String[] parseStringInput(String inputString) throws MizzException {
        assert inputString != null : "Input must not be null";

        String[] parsedParts = new String[4];
        String[] parts = inputString.split("\\s+");
        parsedParts[0] = parts[0].toLowerCase();

        if (parsedParts[0].equals("mark") || parsedParts[0].equals("unmark")) {
            Validator.verifyMarkUnmark(parts);
            parsedParts[1] = parts[1];
            return parsedParts;
        }

        if (parsedParts[0].equals("delete")) {
            Validator.verifyDelete(parts);
            parsedParts[1] = parts[1];
            return parsedParts;
        }

        if (parsedParts[0].equals("find")) {
            Parser.parseFind(parts, parsedParts);
            return parsedParts;
        }

        if (parsedParts[0].equals("list") || parsedParts[0].equals("bye")) {
            return parsedParts;
        }

        switch (parsedParts[0]) {
        case "todo":
            Parser.parseTodo(parts, parsedParts);
            return parsedParts;
        case "deadline":
            Parser.parseDeadline(parts, parsedParts);
            return parsedParts;
        case "event":
            Parser.parseEvent(parts, parsedParts);
            return parsedParts;
        default:
            break;
        }

        return parsedParts;
    }

    /**
     * Helper method to parse a todo command.
     *
     * @param parts Split input string.
     * @param parsedParts Extra details inside the input string.
     * @return An array of strings containing the broken up and cleaned command.
     * @throws ToDoException if verification of the command fails.
     */
    private static String[] parseTodo(String[] parts, String[] parsedParts) throws ToDoException {
        Validator.verifyTodo(parts);
        parsedParts[1] = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        return parsedParts;
    }

    private static String[] parseDeadline(String[] parts, String[] parsedParts)
            throws DeadlineException {
        int byIdx = -1;
        for (int i = 1; i < parts.length; i++) {
            if (byIdx == -1 && parts[i].equals("/by")) {
                byIdx = i;
                break;
            }
        }

        Validator.verifyDeadline(parts, byIdx);
        parsedParts[1] = String.join(" ", Arrays.copyOfRange(parts, 1, byIdx));
        parsedParts[2] = String.join(" ", Arrays.copyOfRange(parts, byIdx + 1, parts.length));
        return parsedParts;
    }

    private static String[] parseEvent(String[] parts, String[] parsedParts) throws EventException {
        int fromIdx = -1;
        int toIdx = -1;

        for (int i = 1; i < parts.length; i++) {
            if (fromIdx == -1 && parts[i].equals("/from")) {
                fromIdx = i;
            } else if (toIdx == -1 && parts[i].equals("/to")) {
                toIdx = i;
            }
        }
        Validator.verifyEvent(parts, fromIdx, toIdx);
        parsedParts[1] = String.join(" ", Arrays.copyOfRange(parts, 1, fromIdx));
        parsedParts[2] = String.join(" ", Arrays.copyOfRange(parts, fromIdx + 1, toIdx));
        parsedParts[3] = String.join(" ", Arrays.copyOfRange(parts, toIdx + 1, parts.length));
        return parsedParts;
    }

    private static String[] parseFind(String[] parts, String[] parsedParts) throws FindException {
        Validator.verifyFind(parsedParts);
        parsedParts[1] = String.join(" ", Arrays.copyOfRange(parts, 1, parts.length));
        return parsedParts;
    }
}
