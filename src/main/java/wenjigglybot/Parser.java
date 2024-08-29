package wenjigglybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {

    /**
     * Processes the input string representing an event task.
     * Extracts the event description, start time, and end time.
     *
     * @param task The input string containing the event details.
     * @return A string array containing the event description, start time, and end time.
     * @throws EventException If the input string does not contain the correct format.
     */
    public static String[] processEventTask(String task) throws EventException {
        String[] fromParts = task.split("/from");
        if (fromParts.length != 2) {
            throw new EventException();
        }
        String[] toParts = fromParts[1].split("/to");
        if (toParts.length != 2) {
            throw new EventException();
        }
        // Extract the event description, start time, and end time
        String event = fromParts[0].replaceFirst("event", "").trim();
        String startTime = toParts[0].trim();
        String endTime = toParts[1].trim();
        return new String[]{event, startTime, endTime};
    }

    /**
     * Processes the input string representing a deadline task.
     * Extracts the task name and deadline.
     *
     * @param task The input string containing the deadline details.
     * @return A string array containing the task name and deadline.
     * @throws DeadlineException If the input string does not contain the correct format.
     */
    public static String[] processDeadlineTask(String task) throws DeadlineException {
        // Remove deadline tag
        String taskNameAndDeadline = task.replaceFirst("deadline", "").trim();

        // Split the title and deadline
        String[] parts = taskNameAndDeadline.split("/by");
        if (parts.length != 2) {
            throw new DeadlineException();
        }
        return parts;
    }

    /**
     * Parses the input command string to determine the corresponding command.
     *
     * @param command The input command string.
     * @return The corresponding {@link Command} enum value.
     * @throws InvalidCommandException If the command is not recognized.
     */
    public static Command parseCommand(String command) throws InvalidCommandException {
        for (Command cmd : Command.values()) {
            if (command.startsWith(cmd.name().toLowerCase())) {
                return cmd;
            }
        }
        throw new InvalidCommandException();
    }

    /**
     * Parses a string representation of a task and returns the corresponding Task object.
     *
     * @param line The string representing the task.
     * @return The corresponding {@link Task} object, or null if the task could not be parsed.
     */
    public static Task parseTask(String line) {
        // Example format: 1. [T][X] task description
        String[] parts = line.split(" ", 3);
        if (parts.length < 3) {
            return null;
        }

        String taskType = parts[1].substring(1, 2);
        boolean isDone = parts[1].charAt(3) == 'X';
        String description = parts[2];

        switch (taskType) {
        case "T": // ToDo task
            ToDoTask todo = new ToDoTask(description.split(" ")[1]);
            if (isDone) {
                todo.markTask();
            }
            return todo;
        case "D": // Deadline task
            // Format: [D][ ] description (by: date/time)
            String[] deadlineParts = description.split("\\(by: ");
            if (deadlineParts.length == 2) {
                String unprocessedTaskDescription = deadlineParts[0].trim();
                String[] splitted = unprocessedTaskDescription.split(" ");
                String taskDescription;
                if (splitted.length != 1) {
                    taskDescription = splitted[1];
                } else {
                    taskDescription = splitted[0];
                }
                String deadline = deadlineParts[1].replace(")", "").trim();
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
                DeadlineTask deadlineTask = new DeadlineTask(taskDescription, LocalDate.parse(deadline, formatter));
                if (isDone) {
                    deadlineTask.markTask();
                }
                return deadlineTask;
            }
            break;
        case "E": // Event task
            // Format: [E][ ] description (from: start time to: end time)
            String[] eventParts = description.split("\\(from: ");
            if (eventParts.length == 2) {
                String taskDescription = eventParts[0].trim();
                String[] timeParts = eventParts[1].split(" to: ");
                if (timeParts.length == 2) {
                    String startTime = timeParts[0].trim();
                    String endTime = timeParts[1].replace(")", "").trim();
                    EventTask eventTask = new EventTask(taskDescription, startTime, endTime);
                    if (isDone) {
                        eventTask.markTask();
                    }
                    return eventTask;
                }
            }
            break;
        }

        return null; // Return null if the task could not be parsed
    }
}