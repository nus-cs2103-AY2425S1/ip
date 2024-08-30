package parser;

import task.TaskList;

/**
 * The {@code Parser} class interprets user input and translates it into commands that modify the {@code TaskList}.
 * It validates the input format before performing actions like marking, unmarking, deleting tasks, or adding new tasks.
 */
public class Parser {

    /**
     * Parses the input message for marking a task as done and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "mark 1").
     * @param t The {@code TaskList} where the task will be marked as done.
     * @throws BobException If the message format is invalid.
     */
    public void markTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^mark \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        t.markTask(num - 1);
    }

    /**
     * Parses the input message for unmarking a task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "unmark 1").
     * @param t The {@code TaskList} where the task will be unmarked.
     * @throws BobException If the message format is invalid.
     */
    public void unmarkTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^unmark \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        t.unmarkTask(num - 1);
    }

    /**
     * Parses the input message for deleting a task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "delete 1").
     * @param t The {@code TaskList} where the task will be deleted.
     * @throws BobException If the message format is invalid.
     */
    public void deleteTaskParser(String message, TaskList t) throws BobException {
        if (!message.matches("^delete \\d+$")) {
            throw new BobException("Invalid format");
        }
        int num = Integer.parseInt(message.replaceAll("[^0-9]", ""));
        t.deleteTask(num - 1);
    }

    /**
     * Parses the input message for adding a ToDo task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "todo read book").
     * @param t The {@code TaskList} where the ToDo task will be added.
     * @throws BobException If the message format is invalid or the description is empty.
     */
    public void addToDoParser(String message, TaskList t) throws BobException {
        if (!message.matches("^todo.*")) {
            throw new BobException("Invalid format");
        }
        String x = message.replaceFirst("todo ", "");
        if (x.isEmpty()) {
            throw new BobException("OOPS!!! The description of a todo cannot be empty.");
        }
        t.addToDo(x);
    }

    /**
     * Parses the input message for adding a Deadline task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "deadline submit report /by 2024-12-31").
     * @param t The {@code TaskList} where the Deadline task will be added.
     * @throws BobException If the message format is invalid or the deadline description or end date is empty.
     */
    public void addDeadlineParser(String message, TaskList t) throws BobException {
        if (!message.matches("^deadline .* \\/by \\d{4}-\\d{2}-\\d{2}$")) {
            throw new BobException("Invalid format");
        }
        String x = message.replaceFirst("deadline ", "");
        String[] parts = x.split(" /by ");
        if (parts.length != 2) {
            throw new BobException("OOPS!!! The description/start time of a deadline cannot be empty.");
        }
        t.addDeadline(parts[0].trim(), parts[1].trim());
    }

    /**
     * Parses the input message for adding an Event task and applies the command to the given {@code TaskList}.
     *
     * @param message The input message to be parsed (e.g., "event project meeting /from 2024-09-01 /to 2024-09-03").
     * @param t The {@code TaskList} where the Event task will be added.
     * @throws BobException If the message format is invalid or the event description/start time/end time is empty.
     */
    public void addEventParser(String message, TaskList t) throws BobException {
        if (!message.matches("^event .* \\/from \\d{4}-\\d{2}-\\d{2} \\/to \\d{4}-\\d{2}-\\d{2}$")) {
            throw new BobException("Invalid format");
        }
        String x = message.replaceFirst("event ", "");
        String[] parts = x.split(" /from | /to");
        if (parts.length != 3) {
            throw new BobException("OOPS!!! The description/start time/end time of an event cannot be empty.");
        }
        t.addEvent(parts[0].trim(), parts[1].trim(), parts[2].trim());
    }

    public void findParser(String message, TaskList t) throws BobException {
        String[] words = message.split(" ",2);
        if (words.length < 2) {
            throw new BobException("Text after find cannot be empty!");
        }
        String name = words[1];
        t.findTasks(name);
    }
}
