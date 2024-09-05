package otto;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Parses user input and tasks from storage.
 */
public class Parser {
    /**
     * Parses the task input by user and returns the task information.
     * @param type The type of the task. Can be "todo", "deadline" or "event".
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the type doesn't match any of the 3 options.
     */
    public static String[] parseTask(String type, String task) throws OttoException {
        return switch (type) {
            case "todo" -> parseTodoTask(task);
            case "deadline" -> parseDeadlineTask(task);
            case "event" -> parseEventTask(task);
            default -> throw new OttoException(OttoResponses.unknownCommandError);
        };
    }

    /**
     * Parses Todo task.
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the task doesn't match the format of a todo task.
     */
    private static String[] parseTodoTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("todo (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            return new String[]{"todo", description};
        } else {
            throw new OttoException(OttoResponses.todoError);
        }
    }

    /**
     * Parses Deadline task.
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the task doesn't match the format of a deadline task.
     */
    private static String[] parseDeadlineTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("deadline (\\S.*) /by (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String deadline = matcher.group(2);
            return new String[]{"deadline", description, deadline};
        } else {
            throw new OttoException(OttoResponses.deadlineError);
        }
    }

    /**
     * Parses Event task.
     * @param task The task string input by user.
     * @return Array of strings containing the command type and task information.
     * @throws OttoException If the task doesn't match the format of an event task.
     */
    private static String[] parseEventTask(String task) throws OttoException {
        Pattern pattern = Pattern.compile("event (\\S.*) /from (\\S.*) /to (\\S.*)", Pattern.CASE_INSENSITIVE);
        Matcher matcher = pattern.matcher(task);
        if (matcher.matches()) {
            String description = matcher.group(1);
            String from = matcher.group(2);
            String to = matcher.group(3);
            return new String[]{"event", description, from, to};
        } else {
            throw new OttoException(OttoResponses.eventError);
        }
    }

    /**
     * Parses the index of mark and unmark commands.
     * @param command Array of strings containing the command and index.
     * @return Index of the task to be marked.
     * @throws OttoException If the command contains a non-integer index or doesn't contain an index.
     */
    public static int parseMarkComplete(String[] command) throws OttoException {
        if (command.length <= 1) {
            throw new OttoException(OttoResponses.markError);
        }
        try {
            return Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    /**
     * Parses the index of delete command.
     * @param command Array of strings containing the command and index.
     * @return Index of the task to be deleted.
     * @throws OttoException If the command contains a non-integer index or doesn't contain an index.
     */
    public static int parseDeleteTask(String[] command) throws OttoException {
        if (command.length <= 1) {
            throw new OttoException(OttoResponses.deleteError);
        }
        try {
            return Integer.parseInt(command[1]);
        } catch (NumberFormatException e) {
            throw new OttoException(OttoResponses.indexError);
        }
    }

    /**
     * Parses task stored in the storage file.
     * Return null if the task doesn't match the format.
     *
     * @param taskStr Raw string of the task.
     * @return A task.
     */
    public static Task parseTasksFromStorage(String taskStr) {
        Pattern pattern = Pattern.compile("\\[(T|D|E)]\\[( |X)] (.+?)(\\(by: (\\S.+?)\\))?(\\(from: (\\S.+?), to: (\\S.+?)\\))?");
        Matcher matcher = pattern.matcher(taskStr);

        if (matcher.matches()) {
            String type = matcher.group(1);
            boolean isComplete = matcher.group(2).equals("X");
            String description = matcher.group(3).trim();
            String by = matcher.group(5);
            String from = matcher.group(7);
            String to = matcher.group(8);
            switch (type) {
            case "T":
                return new Todo(description, isComplete);
            case "D":
                if (by == null) {
                    return null;
                }
                return new Deadline(description, by, isComplete);
            case "E":
                if (from == null || to == null) {
                    return null;
                }
                return new Event(description, from, to, isComplete);
            }
        }
        return null;
    }
}
