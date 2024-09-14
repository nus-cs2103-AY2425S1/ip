package reo.tasks;

import reo.storage.TaskStorage;

import java.util.ArrayList;
import java.util.Arrays;

/** Parses a given user input related to task management */
public class TaskParser {
    enum Command {
        BYE,
        LIST,
        TODO,
        EVENT,
        DEADLINE,
        MARK,
        UNMARK,
        DELETE,
        FIND,
        UNKNOWN,
    }
    private String input;
    private String[] words;
    private Command command;
    private TaskList tasks;
    private TaskStorage storage;

    /**
     * Constructor for the Parser class.
     *
     * @param input The raw user input from the UI.
     * @param tasks The list of tasks in the user's list at the time of the input.
     * @param storage The storage object for writing to memory.
     */
    public TaskParser(String input, TaskList tasks, TaskStorage storage) {
        assert input != null : "Input string should not be null";
        assert tasks != null : "TaskList should not be null";
        assert storage != null : "Storage should not be null";

        this.input = input;
        this.tasks = tasks;
        this.words = input.split("\\s+");
        this.storage = storage;

        try {
            this.command = Command.valueOf(words[0].toUpperCase());
        } catch (IllegalArgumentException e) {
            this.command = Command.UNKNOWN;
        }
    }

    /**
     * Interpret user's command, make the necessary file changes,
     * and display the corresponding UI.
     *
     * @return The String representation of the response
     * for the corresponding command.
     */
    public String parse() {
        switch (command) {
        case LIST:
            return handleList();
        case TODO:
            return handleTodo();
        case MARK:
            return handleMark();
        case UNMARK:
            return handleUnmark();
        case DEADLINE:
            return handleDeadline();
        case EVENT:
            return handleEvent();
        case DELETE:
            return handleDelete();
        case FIND:
            return handleFind();
        case BYE:
            return handleBye();
        case UNKNOWN:
            return handleUnknown();
        default:
            return handleUnknown();
        }
    }

    /**
     * Gets the correct UI for the LIST command.
     *
     * @return The String representation of the stored ArrayList so far.
     */
    public String handleList() {
        return tasks.toString();
    }

    /**
     * Gets the correct UI for the TODO command.
     *
     * @return The String representation of the added Task,
     * with a confirmation message.
     */
    public String handleTodo() {
        final String TODO_CONFIRMATION = "I've added this todo:\n ";
        final String TODO_ERROR = "Please enter a valid task name.\n";

        try {
            String[] parts = input.split(" ", 2);
            Todo toPush = new Todo(parts[1], false);
            tasks.addTodo(toPush);
            storage.writeFile(toPush);
            return TODO_CONFIRMATION + toPush.toString() + "\n";
        } catch (ArrayIndexOutOfBoundsException e) {
            return TODO_ERROR;
        }
    }

    /**
     * Gets the correct UI for the MARK command.
     *
     * @return The String representation of the marked Task,
     * with a confirmation message.
     */
    public String handleMark() {
        final String MARK_CONFIRMATION = "Good job! I've marked this item as done:\n";
        final String MARK_ERROR = "Please enter a valid task number.\n";
        try {
            tasks.markTask(Integer.valueOf(words[1]) - 1);
            storage.markLine(Integer.parseInt(words[1]));
            Task toMark = tasks.get(Integer.parseInt(words[1]) - 1);
            return MARK_CONFIRMATION + toMark.toString() + "\n";
        } catch (IndexOutOfBoundsException e) {
            return MARK_ERROR;
        }
    }

    /**
     * Gets the correct UI for the UNMARK command.
     *
     * @return The String representation of the unmarked Task,
     * with a confirmation message.
     */
    public String handleUnmark() {
        final String UNMARK_CONFIRMATION = "Get better, I've marked this item as not done:\n";
        final String UNMARK_ERROR = "Please enter a valid task number.\n";
        try {
            tasks.unmarkTask(Integer.valueOf(words[1]) - 1);
            storage.unmarkLine(Integer.valueOf(words[1]));
            Task toUnmark = tasks.get(Integer.valueOf(words[1]) - 1);
            return UNMARK_CONFIRMATION + toUnmark.toString() + "\n";
        } catch (IndexOutOfBoundsException e) {
            return UNMARK_ERROR;
        }
    }

    /**
     * Parses a DEADLINE command to extract the relevant information.
     *
     * @param input The input string from the user.
     * @return An array with the name and deadline of the task.
     */

    public String[] parseDeadline(String input) {
        String[] parts = input.split("/by", 2);
        String[] firstPart = parts[0].split(" ", 2);

        String name = firstPart[1];
        String deadline = parts[1].trim();
        return new String[]{name, deadline};
    }

    /**
     * Gets the correct UI for the DEADLINE command.
     *
     * @return The String representation of the marked Task,
     * with a confirmation message.
     */
    public String handleDeadline() {
        final String DEADLINE_CONFIRMATION = "I've added this deadline:\n";
        final String DEADLINE_ERROR = "Please enter a valid task name and deadline.\n";
        try {
            String name = parseDeadline(input)[0];
            String deadline = parseDeadline(input)[1];
            Deadline deadlineToPush = new Deadline(name, false, deadline);
            if (deadlineToPush.dateToString() == null) {
                final String INVALID_DATE_FORMAT_ERROR = "ERROR: Invalid date format (Usage: yyyy-mm-dd).";
                return INVALID_DATE_FORMAT_ERROR;
            }
            tasks.addDeadline(deadlineToPush);
            storage.writeFile(deadlineToPush);
            final String TASKS_LEFT_CONFIRMATION = "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
            return DEADLINE_CONFIRMATION + deadlineToPush.toString() + "\n"
                    + TASKS_LEFT_CONFIRMATION;
        } catch (ArrayIndexOutOfBoundsException e) {
            return DEADLINE_ERROR;
        }
    }

    public String[] parseEvent(String input) {
        String[] parts = input.split("/from", 2);
        String[] firstPart = parts[0].split(" ", 2);
        String[] secondPart = parts[1].split("/to", 2);

        String name = firstPart[1];
        String from = secondPart[0].trim();
        String to = secondPart[1].trim();

        return new String[]{name, from, to};
    }

    /**
     * Gets the correct UI for the EVENT command.
     *
     * @return The String representation of the added Task,
     * with a confirmation message.
     */
    public String handleEvent() {
        final String EVENT_CONFIRMATION = "I've added this event:\n";
        final String EVENT_ERROR = "Please enter a valid task name and to & from dates.\n";
        try {
            String name = parseEvent(input)[0];
            String from = parseEvent(input)[1];
            String to = parseEvent(input)[2];

            Event eventToPush = new Event(name, false, to, from);
            tasks.addEvent(eventToPush);
            storage.writeFile(eventToPush);
            final String TASKS_LEFT_CONFIRMATION = "Now, you have " + tasks.getSize() + " task(s) in your list.\n";
            return EVENT_CONFIRMATION + eventToPush.toString() + "\n"
                    + TASKS_LEFT_CONFIRMATION;
        } catch (ArrayIndexOutOfBoundsException e) {
            return EVENT_ERROR;
        }
    }

    /**
     * Gets the correct UI for the DELETE command.
     *
     * @return The String representation of the deleted Task,
     * with a confirmation message.
     */
    private String handleDelete() {
        final String DELETE_CONFIRMATION = "I've deleted these tasks:\n";
        final String DELETE_ERROR = "Please enter one or more valid task numbers.\n";
        try {
            TaskList toDelete = new TaskList(new ArrayList<Task>());
            ArrayList<Integer> indexes = new ArrayList<>();
            Arrays.sort(words, 1, words.length);
            for (int i = 1; i < words.length; i++) {
                int index = Integer.valueOf(words[i]) - 1;
                indexes.add(index);
                toDelete.add(tasks.get(index));
                storage.removeLine(index + 1);
            }
            tasks.deleteTasks(indexes);
            return DELETE_CONFIRMATION + toDelete.toString();
        } catch (IndexOutOfBoundsException e) {
            return DELETE_ERROR;
        }
    }

    /**
     * Gets the correct UI for the FIND command.
     *
     * @return The String representation of the matching Tasks
     */
    public String handleFind() {
        final String FIND_CONFIRMATION = "Here are the matching tasks in your list:\n";
        final String FIND_ERROR = "ERROR: Please enter a valid keyword to search for.\n";
        try {
            String keyword = words[1];
            TaskList filtered = new TaskList(tasks.filter(keyword));
            return FIND_CONFIRMATION + filtered.toString() + "\n";
        } catch (IndexOutOfBoundsException e) {
            return FIND_ERROR;
        }
    }

    /**
     * Gets the correct UI for the BYE command.
     *
     * @return The goodbye message.
     */
    public String handleBye() {
        final String BYE_MESSAGE = "Bye!";
        return BYE_MESSAGE;
    }

    /**
     * Returns null when the user does not enter a valid command.
     *
     * @return null;
     */
    public String handleUnknown() {
        return null;
    }

}
