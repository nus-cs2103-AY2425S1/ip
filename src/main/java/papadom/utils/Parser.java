package papadom.utils;

import papadom.exceptions.IncorrectTaskInputFormatException;
import papadom.exceptions.NoTaskNumberException;
import papadom.tasks.Deadline;
import papadom.tasks.Event;
import papadom.tasks.Task;
import papadom.tasks.Todo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Objects;

/**
 * Parser class to handle parsing of user input into tasks.
 */
public class Parser {

    /**
     * Creates a Deadline task from the provided details.
     *
     * @param details The string containing the description and deadline date.
     * @return A Deadline task.
     * @throws IncorrectTaskInputFormatException If the input format is incorrect.
     */
    public Deadline createDeadlineTask(String details) throws IncorrectTaskInputFormatException {
        String[] parts = details.split(" /by ");
        if (Objects.equals(parts[0], "") || parts.length == 1) {
            throw new IncorrectTaskInputFormatException();
        }

        String byString = parts[1].trim();  // This can be in "yyyy-MM-dd" or "yyyy-MM-dd HH:mm" format

        try {
            // Pass the date/time string directly to the Deadline constructor
            return new Deadline(parts[0], byString);
        } catch (Exception e) {
            throw new IncorrectTaskInputFormatException();
        }
    }

    /**
     * Creates an Event task from the provided details.
     *
     * @param details The string containing the description, start time, and end time.
     * @return An Event task.
     * @throws IncorrectTaskInputFormatException If the input format is incorrect.
     */
    public Event createEventTask(String details) throws IncorrectTaskInputFormatException {
        String[] parts = details.split(" /from | /to ");
        boolean isPartsLengthOne = parts.length == 1;
        if (parts[0].isEmpty() || isPartsLengthOne) {
            throw new IncorrectTaskInputFormatException();
        }
        return new Event(parts[0], parts[1], parts[2]);
    }

    public String findKeyword(String command) throws IncorrectTaskInputFormatException {
        // Split into two parts: "find" and the rest of the input
        String[] words = command.split(" ", 2);

        // Check if the command starts with "find" and has a keyword following it
        if (words.length > 1 && words[0].equals("find")) {
            return words[1]; // Return the keyword after "find"
        } else {
            throw new IncorrectTaskInputFormatException();
        }
    }

    public static Task retrieveTask(String line) {
        if (line == null || line.isEmpty()) {
            throw new IllegalArgumentException("Task line cannot be null or empty.");
        }

        // Trim leading/trailing spaces
        line = line.trim();

        // Check the type of task based on the first few characters
        char taskType = line.charAt(1); // Task type is at index 1: T, D, or E

        // Check if the task is marked as done based on the [ ] or [X] marker
        boolean isDone = line.charAt(4) == 'X'; // 'X' indicates the task is completed

        // Extract the description of the task, which starts after the task marker
        String description = line.substring(7).trim(); // Description starts after "[ ] "

        Task task = null;

        // Determine the task type and create the appropriate task object
        switch (taskType) {
            case 'T': // ToDo task
                task = new Todo(description); // Create a new ToDo task with the description
                break;

            case 'D': // Deadline task
                // Find the deadline marker "(by: <date>)" in the string
                int byIndex = description.lastIndexOf("(by: ");
                String deadline = description.substring(byIndex + 5, description.length() - 1); // Extract the 'by' string
                String deadlineDescription = description.substring(0, byIndex).trim(); // Extract description without deadline
                task = new Deadline(deadlineDescription, deadline); // Pass the deadline string directly
                break;

            case 'E': // Event task
                // Find the event marker "(from: <start> to: <end>)" in the string
                int fromIndex = description.lastIndexOf("(from: ");
                int toIndex = description.lastIndexOf(" to: ");
                String from = description.substring(fromIndex + 7, toIndex); // Extract start time
                String to = description.substring(toIndex + 5, description.length() - 1); // Extract end time
                String eventDescription = description.substring(0, fromIndex).trim(); // Extract description without time
                task = new Event(eventDescription, from, to); // Create a new Event task
                break;

            default:
                throw new IllegalArgumentException("Unknown task type: " + taskType);
        }

        // Mark the task as done if it was marked in the file
        if (isDone) {
            task.markAsDone();
        }

        return task;
    }

    /**
     * Extracts the task index from the given text string.
     * The input text is expected to contain a task number as the second word.
     * The method splits the text by spaces and attempts to convert the second element to an integer.
     * If the input text doesn't contain at least two elements or the second element is not a valid integer,
     * a {@link NoTaskNumberException} is thrown.
     *
     * @param text the input string from which to extract the task index
     * @return the task index (0-based), derived from the task number in the text
     * @throws NoTaskNumberException if the text does not contain a valid task number
     */
    public static int extractTaskIndex(String text) throws NoTaskNumberException {
        String[] parts = text.split(" ");
        if (parts.length <= 1) {
            throw new NoTaskNumberException();
        }
        try {
            return Integer.parseInt(parts[1]) - 1;
        } catch (NumberFormatException e) {
            throw new NoTaskNumberException();
        }
    }
}
