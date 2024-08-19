import java.util.Arrays;

import executable.AddTask;
import executable.DeleteTask;
import executable.Executable;
import executable.Exit;
import executable.ListTask;
import executable.MarkTask;
import executable.UnmarkTask;
import executable.Warning;

import task.Deadline;
import task.Event;
import task.Todo;

import exception.BotException;
import exception.CommandNotFoundException;
import exception.InvalidArgumentException;
import exception.UnexpectedArgumentException;

/**
 * A parser to parse user's input from YihuiBot and call the appropriate executable.
 *
 * @author Toh Yi Hui A0259080A
 */
public class Parser {
     /**
     * Parse the user's input, breaking it down into a command and an array of
     * arguments. Based on what command and arguments were given, the Parser will
     * instantiate the appropriate executable with the appropriate arguments. This
     * executable can then be separately executed to get its output.
     *
     * @param input the user's input.
     * @return the appropriate executable instantiated with the arguments.
     */
    public Executable parse(String input) throws NullPointerException {
        if (input == null) {
            throw new NullPointerException("User's input cannot be null.");
        }

        String[] inputArray = input.split(" ");
        String command = inputArray.length < 1 ? "" : inputArray[0];
        String[] arguments = inputArray.length < 2
                ? null
                : Arrays.copyOfRange(inputArray, 1, inputArray.length);

        try {
            switch (command) {
            case "bye":
                return bye(arguments);
            case "list":
                return list(arguments);
            case "mark":
                return mark(arguments);
            case "unmark":
                return unmark(arguments);
            case "todo":
                return todo(arguments);
            case "deadline":
                return deadline(arguments);
            case "event":
                return event(arguments);
            case "delete":
                return delete(arguments);
            default:
                throw new CommandNotFoundException(input);
            }
        } catch (BotException e) {
            return new Warning(e.getMessage());
        }
    }

    private Executable bye(String[] arguments) throws UnexpectedArgumentException {
        if (arguments != null) {
            String output = "Unexpected argument called with bye.";
            throw new UnexpectedArgumentException(output);
        }
        return new Exit();
    }

    private Executable list(String[] arguments) throws UnexpectedArgumentException {
        if (arguments != null) {
            String output = "Unexpected argument called with list.";
            throw new UnexpectedArgumentException(output);
        }
        return new ListTask();
    }

    private Executable mark(String[] arguments) throws InvalidArgumentException {
        String sample = "mark 2";

        if (arguments == null) {
            String output = "Please call mark with an integer.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (arguments.length > 1) {
            String output = "Too many arguments. Please call mark with only 1 integer.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new MarkTask(idx);
        } catch (NumberFormatException e) {
            String output = "Invalid argument. Please call mark with an integer.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }
    }

    private Executable unmark(String[] arguments) throws InvalidArgumentException {
        String sample = "unmark 2";

        if (arguments == null) {
            String output = "Please call unmark with an integer.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (arguments.length > 1) {
            String output = "Too many arguments. Please call unmark with only 1 integer.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new UnmarkTask(idx);
        } catch (NumberFormatException e) {
            String output = "Invalid argument. Please call mark with an integer.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }
    }

    private Executable todo(String[] arguments) throws InvalidArgumentException {
        String sample = "todo read book";

        if (arguments == null || arguments.length < 1) {
            String output = "Please specify a task description as argument.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }

        String description = String.join(" ", arguments);
        Todo task = new Todo(description);
        return new AddTask(task);
    }

    private Executable deadline(String[] arguments) throws InvalidArgumentException {
        String sample = "deadline return book /by Sunday";

        if (arguments == null) {
            String output = "Please specify a task description and a deadline as argument.\n"
                    + "E.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        int idx = findIndexOfStringInArray(arguments, "/by");
        
        if (idx < 0) {
            String output = "Please indicate a deadline using '/by'.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (idx == 0) {
            String output = "Please indicate a task description.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }
        if (idx == arguments.length - 1) {
            String output = "Please indicate a deadline after '/by'.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        String description = sliceAndJoinAt(arguments, 0, idx, " ");
        String deadline = sliceAndJoinAt(arguments, idx + 1, arguments.length, " ");
        Deadline task = new Deadline(description, deadline);
        return new AddTask(task);
    }

    private Executable event(String[] arguments) throws InvalidArgumentException {
        String sample = "event project meeting /from Mon 2pm /to 4pm";

        if (arguments == null) {
            String output = "Please specify a task description, starting and ending time "
                    + "of event as argument.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        int fromIdx = findIndexOfStringInArray(arguments, "/from");
        int toIdx = findIndexOfStringInArray(arguments, "/to");

        if (fromIdx < 0 || toIdx < 0) {
            String output = "Please indicate starting and ending time of event using '/from' "
                    + "and '/to'.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (toIdx < fromIdx) {
            String output = "Please indicate the start time before the end time.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }

        if (fromIdx == 0) {
            String output = "Please indicate a task description.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (toIdx - fromIdx < 2) {
            String output = "Please indicate a start time.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (toIdx == arguments.length - 1) {
            String output = "Please indicate an end time.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        String description = sliceAndJoinAt(arguments, 0, fromIdx, " ");
        String from = sliceAndJoinAt(arguments, fromIdx + 1, toIdx, " ");
        String to = sliceAndJoinAt(arguments, toIdx + 1, arguments.length, " ");
        Event task = new Event(description, from, to);
        return new AddTask(task);
    }

    private Executable delete(String[] arguments) throws InvalidArgumentException {
        String sample = "delete 2";

        if (arguments == null) {
            String output = "Please call delete with an integer.\nE.g. " + sample;
            throw new InvalidArgumentException(output);
        }

        if (arguments.length > 1) {
            String output = "Too many arguments. Please call delete with only 1 integer.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }

        try {
            int idx = Integer.parseInt(arguments[0]);
            return new DeleteTask(idx);
        } catch (NumberFormatException e) {
            String output = "Invalid argument. Please call delete with an integer.\nE.g. "
                    + sample;
            throw new InvalidArgumentException(output);
        }
    }

    /**
     * Find and return the index of String s in a String array.
     *
     * @param array the string array to be searched.
     * @param s the string to search for.
     * @return the index of the String s in given array.
     *         Return -1 if no such String is found.
     */
    private static int findIndexOfStringInArray(String[] array, String s) {
        return Arrays.<String>asList(array).indexOf(s);
    }

    /**
     * Slice the array with range from and to, and then join the remaining array using
     * the given delimiter.
     *
     * @param array the string array to slice.
     * @param from the index of the array to slice from, inclusive.
     * @param to the index of the array to slice to, exclusive.
     * @param delimiter the delimiter that separates each element.
     * @return the resulting String after joining it using delimiter.
     */
    private static String sliceAndJoinAt(String[] array, int from, int to, CharSequence delimiter) {
        return String.join(delimiter, Arrays.<String>copyOfRange(array, from, to));
    }
}
