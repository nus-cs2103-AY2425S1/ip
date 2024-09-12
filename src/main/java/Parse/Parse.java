package Parse;

import Storage.Storage;
import Task.TaskList;
import Ui.Ui;
import Exception.MissingArg;
import Exception.WrongKeyword;
public class Parse {
    private static final int todoParseSplitIndex = 5;
    private static final int deadlineParseSplitIndex = 9;
    private static final int eventParseSplitIndex = 6;
    private static final int findParseSplitIndex = 5;
    private static final String deadlineSplitBy = " /by ";
    private static final String eventSplitBy = " /from | /to ";
    /**
     * Parses a Todo input string to get the task description.
     *
     * @param input the input string
     * @return the task description as a string
     */
    public static String parseTodo(String input) {
        return input.substring(todoParseSplitIndex);
    }
    /**
     * Parses a Deadline input string to get the task description and the deadline.
     *
     * @param input the input string
     * @return an array of strings
     */
    public static String[] parseDeadline(String input) {
        return input.substring(deadlineParseSplitIndex).split(deadlineSplitBy);
    }
    /**
     * Parses an Event input string to get the task description, start time, and end time.
     *
     * @param input the input string
     * @return an array of strings
     */
    public static String[] parseEvent(String input) {
        return input.substring(eventParseSplitIndex).split(eventSplitBy);
    }
    public static String parseFind(String input) {
        return input.substring(findParseSplitIndex);
    }
    /**
     * Initial parsing of user commands and updates the task list,storage and ui accordingly.
     *
     * @param input the user input
     * @param ui the user interface class instance
     * @param tasks the task list class instance
     * @param storage the storage class instance
     * @return false if user entered "bye" and true oterwise
     */
    public static String initialParse(String input, Ui ui, TaskList tasks, Storage storage) {
        if (input.equals("bye")) {
            return ui.uiBye();
        } else if (input.equals("list")) {
            return tasks.handleList();
        } else if (input.startsWith("mark")) {
            storage.writeFile(tasks.getArray());
            return tasks.markDone(input);
        } else if (input.startsWith("unmark")) {
            storage.writeFile(tasks.getArray());
            return tasks.markUnDone(input);
        } else if (input.startsWith("delete")) {
            storage.writeFile(tasks.getArray());
            return tasks.delete(input);
        } else if (input.startsWith("find")) {
            return tasks.search(input);
        }
            try {
                storage.writeFile(tasks.getArray());
                return tasks.handleTask(input);
            } catch (WrongKeyword | MissingArg e) {
                System.out.println(e.getMessage());
            }

        return "Please enter a valid command";
    }
}
