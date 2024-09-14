package seedu.parser;

import seedu.task.TaskList;

/**
 * The {@code Parser} class interprets user input and translates it into commands that modify the {@code TaskList}.
 * It validates the input format before performing actions like marking, unmarking, deleting tasks, or adding new tasks.
 */
public class Parser {

    public boolean validIndex(TaskList t, int num) throws BobException {
        if (num < 0 || num >= t.getLength()) {
            throw new BobException("Invalid index value");
        }
        return true;
    }

    /**:uuuuu
     * Parses the input message for marking a task as done and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "mark 1").
     * @param t The {@code TaskList} where the task will be marked as done.
     * @throws BobException If the message format is invalid.
     */
    public String markTaskAsDoneParser(String message, TaskList t) throws BobException {
        if (!message.matches("^mark \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        validIndex(t, num);
        return t.markTaskAsDone(num - 1);
    }

    /**
     * Parses the input message for unmarking a task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "unmark 1").
     * @param t The {@code TaskList} where the task will be unmarked.
     * @throws BobException If the message format is invalid.
     */
    public String unmarkTaskAsDoneParser(String message, TaskList t) throws BobException {
        if (!message.matches("^unmark \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        validIndex(t, num);
        return t.unmarkTaskAsDone(num - 1);
    }

    /**
     * Parses the input message for deleting a task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "delete 1").
     * @param t The {@code TaskList} where the task will be deleted.
     * @throws BobException If the message format is invalid.
     */
    public String deleteTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^delete \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        validIndex(t, num);
        return t.deleteTask(num - 1);
    }

    /**
     * Parses the input message for adding a ToDo task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "todo read book").
     * @param t The {@code TaskList} where the ToDo task will be added.
     * @throws BobException If the message format is invalid or the description is empty.
     */
    public String addToDoParser(String message, TaskList t) throws BobException {
        if (!message.matches("^todo .*")) {
            throw new BobException("Invalid format: todo [description]");
        }
        String content = message.replaceFirst("todo ", "");
        if (content.isEmpty()) {
            throw new BobException("Invalid format: todo [description]");
        }
        return t.addToDo(content);
    }

    /**
     * Parses the input message for adding a Deadline task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "deadline submit report /by 2024-12-31").
     * @param t The {@code TaskList} where the Deadline task will be added.
     * @throws BobException If the message format is invalid or the deadline description or end date is empty.
     */
    public String addDeadlineParser(String message, TaskList t) throws BobException {
        if (!message.matches("^deadline .* \\/by \\d{4}-\\d{2}-\\d{2}$")) {
            throw new BobException("Invalid format: deadline [description] /by [start time]");
        }
        String content = message.replaceFirst("deadline ", "");
        String[] parts = content.split(" /by ");
        if (parts.length != 2) {
            throw new BobException("Invalid format: deadline [description] /by [start time]");
        }
        return t.addDeadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the input message for adding an Event task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "event project meeting /from 2024-09-01 /to 2024-09-03").
     * @param t The {@code TaskList} where the Event task will be added.
     * @throws BobException If the message format is invalid or the event description/start time/end time is empty.
     */
    public String addEventParser(String message, TaskList t) throws BobException {
        if (!message.matches("^event .* \\/from \\d{4}-\\d{2}-\\d{2} \\/to \\d{4}-\\d{2}-\\d{2}$")) {
            throw new BobException("Invalid format: event [description] /by [start time] /to [end time]");
        }
        String content = message.replaceFirst("event ", "");
        String[] parts = content.split(" /from | /to");
        if (parts.length != 3) {
            throw new BobException("Invalid format: event [description] /by [start time] /to [end time]");
        }
        return t.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    public String findParser(String message, TaskList t) throws BobException {
        String[] words = message.split(" ", 2);
        if (words.length < 2) {
            throw new BobException("Text after find cannot be empty!");
        }
        String name = words[1];
        return t.findTasks(name);
    }
}
