package spiderman;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a Command class that handles various commands to manage tasks.
 * Provides methods to add, delete, mark, unmark, and find tasks in the TaskList.
 */
public class Command {

    /**
     * Adds a new ToDo task to the task list.
     *
     * @param input The user input split into a string array.
     * @param tasks The task list to which the ToDo task is to be added.
     * @return A message indicating the result of the addition.
     */
    public String todo(String[] input, TaskList tasks) {
        assert input != null && input.length > 0 : "Input for todo command should not be null or empty";

        String description = input[0].replaceFirst("todo", "").trim();
        if (description.isEmpty()) {
            return "The description of a todo cannot be empty.";
        }

        return tasks.addTask(new Todo(description));
    }

    /**
     * Adds a new Deadline task to the task list.
     *
     * @param input The user input split into a string array.
     * @param tasks The task list to which the Deadline task is to be added.
     * @return A message indicating the result of the addition.
     */
    public String deadline(String[] input, TaskList tasks) {
        assert input != null && input.length > 1 : "Input for deadline command should have description and date";

        String description = input[0].replaceFirst("deadline", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        if (description.isEmpty()) {
            return "The description of a deadline cannot be empty.";
        }

        try {
            LocalDate by = LocalDate.parse(input[1].replaceFirst("by", "").trim(), formatter);
            return tasks.addTask(new Deadline(description, by));
        } catch (DateTimeParseException e) {
            return "The date is not in the correct format! It should be YYYY-MM-DD";
        } catch (Exception e) {
            return "The stated deadline should have a date!";
        }
    }

    /**
     * Adds a new Event task to the task list.
     *
     * @param input The user input split into a string array.
     * @param tasks The task list to which the Event task is to be added.
     * @return A message indicating the result of the addition.
     */
    public String event(String[] input, TaskList tasks) {
        assert input != null && input.length > 2 : "Input for event command should have description, from and to dates";

        String description = input[0].replaceFirst("event", "").trim();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");

        if (description.isEmpty()) {
            return "The description of an event cannot be empty.";
        }

        String fromString = input[1].replaceFirst("from", "").trim();
        String toString = input[2].replaceFirst("to", "").trim();

        try {
            LocalDateTime from = LocalDateTime.parse(fromString, formatter);
            LocalDateTime to = LocalDateTime.parse(toString, formatter);
            return tasks.addTask(new Event(description, from, to));
        } catch (DateTimeParseException e) {
            return "The date and time is not in the correct format! It should be YYYY-MM-DD HH:mm";
        } catch (Exception e) {
            return "The from and/or to cannot be empty!";
        }
    }

    /**
     * Deletes a task from the task list.
     *
     * @param input The user input split into a string array.
     * @param tasks The task list from which the task is to be deleted.
     * @return A message indicating the result of the deletion.
     */
    public String delete(String[] input, TaskList tasks) {
        assert input != null : "Input for delete command cannot be null";

        if (input.length <= 1) {
            return "Input for delete command should have a task number!";
        }

        try {
            int number = Integer.parseInt(input[1]) - 1;
            return tasks.deleteTask(number);
        }
        catch (NumberFormatException e) {
            return "Detected list number as a non integer value! The right format is delete {list number}";
        }

    }

    /**
     * Lists all tasks in the task list.
     *
     * @param tasks The task list to be listed.
     * @return A formatted string of all tasks in the list.
     */
    public String list(TaskList tasks) {
        return tasks.listTasks();
    }

    /**
     * Finds tasks in the task list that contain a given keyword.
     *
     * @param input The user input containing the keyword to search for.
     * @param tasks The task list to be searched.
     * @return A formatted string of tasks that match the keyword.
     */
    public String find(String input, TaskList tasks) {
        assert tasks != null : "TaskList should not be null";

        String keyword = input.replaceFirst("find", "").trim();
        if (keyword.isEmpty()) {
            return "The keyword for find cannot be empty!";
        } else {
            return tasks.findTasks(keyword);
        }
    }

    /**
     * Marks a task as done in the task list.
     *
     * @param input The user input split into a string array.
     * @param tasks The task list containing the task to be marked as done.
     * @return A message indicating the result of marking the task as done.
     */
    public String mark(String[] input, TaskList tasks) {
        assert input != null : "Input for mark command cannot be null!";

        if (input.length <= 1) {
            return "Input for mark command should have a task number!";
        }

        try {
            int number = Integer.parseInt(input[1]) - 1;
            return tasks.markTaskAsDone(number);
        }
        catch (NumberFormatException e) {
            return "Detected list number as a non integer value! The right format is mark {list number}";
        }
    }

    /**
     * Marks a task as not done in the task list.
     *
     * @param input The user input split into a string array.
     * @param tasks The task list containing the task to be marked as not done.
     * @return A message indicating the result of marking the task as not done.
     */
    public String unmark(String[] input, TaskList tasks) {
        assert input != null : "Input for unmark command should have a task number";

        if (input.length <= 1) {
            return "Input for unmark command should have a task number!";
        }

        try {
            int number = Integer.parseInt(input[1]) - 1;
            return tasks.markTaskAsNotDone(number);
        }
        catch (NumberFormatException e) {
            return "Detected list number as a non integer value! The right format is unmark {list number}";
        }
    }

    /**
     * Exits the program with a goodbye message.
     *
     * @return A goodbye message.
     */
    public String bye() {
        return "Bye. Hope to see you again soon!";
    }
}
