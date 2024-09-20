package seedu.parser;

import seedu.task.TaskList;

/**
 * The {@code Parser} class is responsible for interpreting user input and translating it into specific commands
 * that modify the {@code TaskList}. It supports various commands such as marking tasks as done, unmarking tasks,
 * deleting tasks, adding ToDo, Deadline, and Event tasks, and searching for tasks by name.
 * It validates the format of user inputs and performs appropriate actions based on the command provided.
 */
public class Parser {
    public static final String MARK_TASK_COMMAND = "^mark \\d+$";
    public static final String UNMARK_TASK_COMMAND = "^unmark \\d+$";
    public static final String DELETE_TASK_COMMAND = "^delete \\d+$";
    public static final String ADD_TODO_COMMAND = "^todo .*";
    public static final String ADD_DEADLINE_COMMAND = "^deadline .* \\/by \\d{4}-\\d{2}-\\d{2}$";
    public static final String ADD_EVENT_FORMAT = "^event .* \\/from \\d{4}-\\d{2}-\\d{2} \\/to \\d{4}-\\d{2}-\\d{2}$";
    public static final int DEADLINE_COMMAND_LENGTH = 2;
    public static final int EVENT_COMMAND_LENGTH = 3;

    /**
     * Extracts the task number from the user's input message.
     *
     * @param message The input message containing a command with a task number.
     * @return The extracted task number as an integer.
     */
    private int getNum(String message) {
        return Integer.parseInt(message.replaceAll("[^0-9]", ""));
    }

    /**
     * Validates if the provided task index is within the valid range of the {@code TaskList}.
     *
     * @param tl The {@code TaskList} to validate against.
     * @param num The task index to validate.
     * @return True if the index is valid.
     * @throws BobException If the task index is invalid (out of range).
     */
    public boolean validateIndex(TaskList tl, int num) throws BobException {
        assert tl != null;
        if (num <= 0 || num > tl.getLength()) {
            throw new BobException("Invalid index value");
        }
        return true;
    }

    /**
     * Parses the input message to mark a task as done and applies the action to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "mark 1").
     * @param tl The {@code TaskList} where the task will be marked as done.
     * @return The confirmation message after marking the task as done.
     * @throws BobException If the message format is invalid or if the task index is out of range.
     */
    public String markTaskAsDoneParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        if (!message.matches(MARK_TASK_COMMAND)) {
            throw new BobException("Invalid format");
        }
        final int num = getNum(message);
        validateIndex(tl, num);
        return tl.markTaskAsDone(num - 1);
    }

    /**
     * Parses the input message to unmark a task and applies the action to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "unmark 1").
     * @param tl The {@code TaskList} where the task will be unmarked.
     * @return The confirmation message after unmarking the task.
     * @throws BobException If the message format is invalid or if the task index is out of range.
     */
    public String unmarkTaskAsDoneParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        if (!message.matches(UNMARK_TASK_COMMAND)) {
            throw new BobException("Invalid format");
        }
        final int num = getNum(message);
        validateIndex(tl, num);
        return tl.unmarkTaskAsDone(num - 1);
    }

    /**
     * Parses the input message to delete a task and applies the action to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "delete 1").
     * @param tl The {@code TaskList} where the task will be deleted.
     * @return The confirmation message after deleting the task.
     * @throws BobException If the message format is invalid or if the task index is out of range.
     */
    public String deleteTaskParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        if (!message.matches(DELETE_TASK_COMMAND)) {
            throw new BobException("Invalid format");
        }
        final int num = getNum(message);
        validateIndex(tl, num);
        return tl.deleteTask(num - 1);
    }

    /**
     * Parses the input message to add a ToDo task and applies the action to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "todo read book").
     * @param tl The {@code TaskList} where the ToDo task will be added.
     * @return The confirmation message after adding the ToDo task.
     * @throws BobException If the message format is invalid or the description is empty.
     */
    public String addToDoParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        if (!message.matches(ADD_TODO_COMMAND)) {
            throw new BobException("Invalid format: todo [description]");
        }
        String content = message.replaceFirst("todo ", "");
        if (content.isEmpty()) {
            throw new BobException("Invalid format: todo [description]");
        }
        return tl.addToDo(content);
    }

    /**
     * Parses the input message for adding a Deadline task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "deadline submit report /by 2024-12-31").
     * @param tl The {@code TaskList} where the Deadline task will be added.
     * @return A string indicating the task was added.
     * @throws BobException If the message format is invalid or the deadline description or end date is empty.
     */
    public String addDeadlineParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        if (!message.matches(ADD_DEADLINE_COMMAND)) {
            throw new BobException("Invalid format: deadline [description] /by [due date]");
        }

        String content = message.replaceFirst("deadline ", "");
        String[] parts = content.split(" /by ");
        if (parts.length != DEADLINE_COMMAND_LENGTH) {
            throw new BobException("Invalid format: deadline [description] /by [due date]");
        }
        return tl.addDeadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the input message for adding an Event task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "event project meeting /from 2024-09-01 /to 2024-09-03").
     * @param tl The {@code TaskList} where the Event task will be added.
     * @return A string indicating the task was added.
     * @throws BobException If the message format is invalid or the event description/start time/end time is empty.
     */
    public String addEventParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        if (!message.matches(ADD_EVENT_FORMAT)) {
            throw new BobException("Invalid format: event [description] /from [start time] /to [end time]");
        }

        String content = message.replaceFirst("event ", "");
        String[] parts = content.split(" /from | /to");
        if (parts.length != EVENT_COMMAND_LENGTH) {
            throw new BobException("Invalid format: event [description] /from [start time] /to [end time]");
        }
        return tl.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    /**
     * Parses the input message for finding tasks and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "find book").
     * @param tl The {@code TaskList} where tasks will be searched.
     * @return A string listing tasks that match the search term.
     * @throws BobException If the search term is empty.
     */
    public String findParser(String message, TaskList tl) throws BobException {
        assert tl != null && message != null;
        String[] words = message.split(" ", 2);
        if (words.length < 2) {
            throw new BobException("Text after find cannot be empty!");
        }
        String name = words[1];
        return tl.findTasks(name);
    }
}
