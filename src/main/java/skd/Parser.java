package skd;

<<<<<<< HEAD
import skd.task.Deadline;
import skd.task.Event;
import skd.task.Task;
import skd.task.ToDo;
import skd.task.TaskType;
=======
import skd.CommandType;
import task.Deadline;
import task.Event;
import task.Task;
import task.ToDo;
>>>>>>> ec24a2d9f9afe5e1fa72e4eb8c9a7e7a2c9d1127

import java.util.List;

public class Parser {

    /**
     * Parses user's command and returns the same CommandType.
     *
     * @param input The input command from the user.
     * @return The CommandType matching user's input.
     */
    public static CommandType parseCommand(String input) {
        if (input.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (input.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (input.startsWith("mark")) {
            return CommandType.MARK;
        } else if (input.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (input.startsWith("todo")) {
            return CommandType.TODO;
        } else if (input.startsWith("deadline")) {
            return CommandType.DEADLINE;
        } else if (input.startsWith("event")) {
            return CommandType.EVENT;
        } else if (input.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (input.startsWith("find")) {
            return CommandType.FIND;
        } else {
            return CommandType.ETC;
        }
    }

    /**
     * Parses input to create a new Task object based on command type.
     *
     * @param input Input command from the user.
     * @param type  The CommandType to determine type of task.
     * @return Task object based on input.
     */
    public static Task parseAddCommand(String input, CommandType type) {
        switch (type) {
            case TODO:
                String description = input.substring(5).trim();
                if (description.isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description of a todo cannot be empty.");
                }
                return new ToDo(description);
            case DEADLINE:
                String[] deadlineParts = input.substring(9).split(" /by ");
                if (deadlineParts.length < 2 || deadlineParts[0].trim().isEmpty() || deadlineParts[1].trim().isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description or deadline cannot be empty.");
                }
                return new Deadline(deadlineParts[0].trim(), deadlineParts[1].trim());
            case EVENT:
                String[] eventParts = input.substring(6).split(" /from | /to ");
                if (eventParts.length < 3 || eventParts[0].trim().isEmpty() || eventParts[1].trim().isEmpty() || eventParts[2].trim().isEmpty()) {
                    throw new IllegalArgumentException("OOPS!!! The description, start time, or end time cannot be empty.");
                }
                return new Event(eventParts[0].trim(), eventParts[1].trim(), eventParts[2].trim());
            default:
                throw new IllegalArgumentException("OOPS!!! I'm sorry, but I don't know what that means :-(");
        }
    }

    /**
     * Parses line from the file to recreate Task object.
     *
     * @param line Line from the saved file representing task.
     * @return Task object created from the line.
     */
    public static Task parseTask(String line) {
        try {
            String type = line.substring(1, 2);
            boolean isDone = line.charAt(4) == 'X';
            String description;

            switch (type) {
                case "T":
                    description = line.substring(7);
                    return new ToDo(description, isDone);
                case "D":
                    description = line.substring(7, line.indexOf("(by:")).trim();
                    String by = line.substring(line.indexOf("(by:") + 5, line.indexOf(")")).trim();
                    return new Deadline(description, by, isDone);
                case "E":
                    description = line.substring(7, line.indexOf("(from:")).trim();
                    String from = line.substring(line.indexOf("(from:") + 7, line.indexOf(" to:")).trim();
                    String to = line.substring(line.indexOf("to:") + 4, line.indexOf(")")).trim();
                    return new Event(description, from, to, isDone);
                default:
                    throw new IllegalArgumentException("Invalid task type");
            }
        } catch (Exception e) {
            System.out.println("     Corrupted task entry found and skipped: " + line);
            return null;
        }
    }

    /**
     * Marks task as done based on the input.
     *
     * @param input The input command from user.
     * @param tasks List of tasks.
     */
    public static void parseMark(String input, List<Task> tasks) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
        }
        tasks.get(index).markAsDone();
    }

    /**
     * Unmarks task as not done based on input.
     *
     * @param input The Input command from user.
     * @param tasks List of tasks.
     */
    public static void parseUnmark(String input, List<Task> tasks) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
        }
        tasks.get(index).unmark();
    }

    /**
     * Deletes task from the list based on input.
     *
     * @param input The input command from user.
     * @param tasks List of tasks.
     */
    public static void parseDelete(String input, List<Task> tasks) {
        int index = Integer.parseInt(input.split(" ")[1]) - 1;
        if (index < 0 || index >= tasks.size()) {
            throw new IllegalArgumentException("OOPS!!! The task number is invalid.");
        }
        Task removedTask = tasks.remove(index);
        removedTask.printTaskRemovedMessage(tasks.size());
    }
}