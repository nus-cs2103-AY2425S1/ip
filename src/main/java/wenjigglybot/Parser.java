package wenjigglybot;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Parser {
    private static final String EVENT_KEYWORD = "event";
    private static final String DEADLINE_KEYWORD = "deadline";
    private static final String BY_DELIMITER = "\\(by: ";
    private static final String FROM_DELIMITER = "\\(from: ";


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
        String event = fromParts[0].replaceFirst(EVENT_KEYWORD, "").trim();
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
        String taskNameAndDeadline = task.replaceFirst(DEADLINE_KEYWORD, "").trim();

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
        if (line == null || line.isEmpty()) {
            return null;
        }

        String[] parts = line.split(" ", 3);
        if (parts.length < 3) {
            return null;
        }

        String taskType = parts[1].substring(1, 2);
        boolean isDone = parts[1].length() >= 5 && parts[1].charAt(4) == 'X';
        String description = parts[2];

        switch (taskType) {
        case "T":
            return createToDoTask(description, isDone);
        case "D":
            return createDeadlineTask(description, isDone);
        case "E":
            return createEventTask(description, isDone);
        default:
            return null;
        }
    }

    private static ToDoTask createToDoTask(String description, boolean isDone) {
        ToDoTask todo = new ToDoTask(description);
        if (isDone) {
            todo.markTask();
        }
        return todo;
    }

    private static DeadlineTask createDeadlineTask(String description, boolean isDone) {
        String[] deadlineParts = description.split(BY_DELIMITER);
        if (deadlineParts.length == 2) {
            String taskDescription = extractTaskDescription(deadlineParts[0]);
            String deadline = deadlineParts[1].replace(")", "").trim();
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d MMMM yyyy");
            DeadlineTask deadlineTask = new DeadlineTask(taskDescription, LocalDate.parse(deadline, formatter));
            if (isDone) {
                deadlineTask.markTask();
            }
            return deadlineTask;
        }
        return null;
    }

    private static EventTask createEventTask(String description, boolean isDone) {
        String[] eventParts = description.split(FROM_DELIMITER);
        if (eventParts.length == 2) {
            String taskDescription = extractTaskDescription(eventParts[0]);
            String[] timeParts = eventParts[1].split(" to: ");
            if (timeParts.length == 2) {
                EventTask eventTask = new EventTask(taskDescription, timeParts[0].trim(), timeParts[1].replace(")", "").trim());
                if (isDone) {
                    eventTask.markTask();
                }
                return eventTask;
            }
        }
        return null;
    }

    private static String extractTaskDescription(String description) {
        return description.trim().substring(2);
    }

}