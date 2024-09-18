package parse;

import storage.Storage;
import task.TaskList;
import ui.Ui;
import exception.MissingArg;
import exception.WrongKeyword;
public class Parse {
    private static final int todoParseSplitIndex = 5;
    private static final int deadlineParseSplitIndex = 9;
    private static final int eventParseSplitIndex = 6;
    private static final int findParseSplitIndex = 5;
    private static final String deadlineSplitBy = " /by ";
    private static final String eventSplitBy = " /from | /to ";
    private static final String parseTodoErrorMessage = "Please enter a task for Todo";
    private static final String parseDeadlineErrorMessage = "Wrong number/format of arguments for deadline task!";
    private static final String parseEventErrorMessage = "Wrong number/format of arguments for event task!";
    private static final String parseFindErrorMessage = "Please enter a valid input after the find keyword!";
    /**
     * Parses a Todo input string to get the task description.
     *
     * @param input the input string.
     * @return the task description as a string.
     * @throws MissingArg if the input string does not contain a task description.
     */
    public static String parseTodo(String input) throws MissingArg {
        if (input.replaceAll("\\s", "").length() <= 4) {
            throw new MissingArg(parseTodoErrorMessage);
        }
        return input.substring(todoParseSplitIndex);
    }
    /**
     * Parses a Deadline input string to get the task description and the deadline.
     *
     * @param input the input string.
     * @return an array of strings.
     * @throws MissingArg if the input string does not contain a valid task description format.
     */
    public static String[] parseDeadline(String input) throws MissingArg {
        String[] s = input.substring(deadlineParseSplitIndex).split(deadlineSplitBy);
        if (s.length != 2) {
            throw new MissingArg(parseDeadlineErrorMessage);
        }
        return s;
    }
    /**
     * Parses an Event input string to get the task description, start time, and end time.
     *
     * @param input the input string.
     * @return an array of strings.
     * @throws MissingArg if the input string does not contain a valid task description format.
     */
    public static String[] parseEvent(String input) throws MissingArg {
        if (!input.contains("/from") || !input.contains("/to")) {
            throw new MissingArg(parseEventErrorMessage);
        }
        String[] s = input.substring(eventParseSplitIndex).split(eventSplitBy);
        if (s.length != 3) {
            throw new MissingArg(parseEventErrorMessage);
        }
        return s;
    }
    /**
     * Parses the input string to extract the search keyword for the "find" command.
     *
     * @param input the input string containing the find command and search keyword.
     * @return the search keyword as a string.
     */
    public static String parseFind(String input) throws MissingArg {
        if (input.replaceAll("\\s", "").length() <= 4) {
            throw new MissingArg(parseFindErrorMessage);
        }
        return input.substring(findParseSplitIndex);
    }
    /**
     * Parses user commands and updates the task list,storage and ui accordingly.
     *
     * @param input the user input.
     * @param ui the user interface class instance.
     * @param tasks the task list class instance.
     * @param storage the storage class instance.
     * @return String after handling the input.
     */
    public static String initialParse(String input, Ui ui, TaskList tasks, Storage storage) {
        if (input.equals("bye")) {
            return ui.uiBye();
        } else if (input.equals("list")) {
            return tasks.handleList();
        } else if (input.startsWith("mark")) {
            return tasks.markDone(input, storage);
        } else if (input.startsWith("unmark")) {
            return tasks.markUnDone(input, storage);
        } else if (input.startsWith("delete")) {
            return tasks.delete(input, storage);
        } else if (input.startsWith("find ")) {
            return tasks.search(input);
        } else if (input.equals("sort")) {
            return tasks.sort(storage);
        }
        try {
            return tasks.handleTask(input, storage);
        } catch (WrongKeyword e) {
            System.out.println(e.getMessage());
            return e.getMessage();
        }
    }
}
