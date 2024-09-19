package pixy.parser;

import pixy.PixyExceptions;
import pixy.tasks.Deadlines;
import pixy.tasks.Event;
import pixy.tasks.Task;
import pixy.tasks.TaskList;
import pixy.tasks.ToDos;
import pixy.ui.Ui;

import java.time.format.DateTimeParseException;
import java.util.regex.Pattern;

/**
 * Parses the user commands and returns the command type.
 */
public class Parser {

    /**
     * Normalizes the command by trimming and removing excess spaces.
     *
     * @param command The raw user input.
     * @return A normalized command with single spaces between words.
     */
    private String normalizeCommand(String command) {
        return command.trim().replaceAll("\\s+", " ");
    }

    /**
     * Checks for unexpected special characters in task descriptions.
     *
     * @param input The task description or command input.
     * @throws PixyExceptions If the input contains invalid characters.
     */
    private void validateNoSpecialChars(String input) throws PixyExceptions {
        if (!Pattern.matches("[\\w\\s/]+", input)) {
            throw new PixyExceptions("OOPS!!! Special characters are not allowed in the description.");
        }
    }

    /**
     * Determines the command type from the user's input.
     *
     * @param command User inputted command
     * @return CommandType The type of the command
     */
    public CommandType parseCommandType(String command) {
        assert command != null : "Command cannot be null";
        command = normalizeCommand(command);

        if (command.equalsIgnoreCase("list")) {
            return CommandType.LIST;
        } else if (command.equalsIgnoreCase("bye")) {
            return CommandType.BYE;
        } else if (command.startsWith("mark")) {
            return CommandType.MARK;
        } else if (command.startsWith("unmark")) {
            return CommandType.UNMARK;
        } else if (command.startsWith("delete")) {
            return CommandType.DELETE;
        } else if (command.startsWith("find")) {
            return CommandType.FIND;
        } else if (command.startsWith("todo ")) {
            return CommandType.TODO;
        } else if (command.startsWith("deadline ")) {
            return CommandType.DEADLINE;
        } else if (command.startsWith("event ")) {
            return CommandType.EVENT;
        } else {
            return CommandType.UNKNOWN;
        }
    }

    /**
     * Parses user command and performs action corresponding to the command type.
     *
     * @param command User inputted command
     * @param tasks The task list
     * @param ui The interaction of chatbot according to the command.
     * @return A string response that can be returned to the user.
     */
    public String executeCommand(String command, TaskList tasks, Ui ui) {
        assert command != null : "Command cannot be null";
        assert tasks != null : "TaskList cannot be null";
        assert ui != null : "Ui cannot be null";

        command = normalizeCommand(command);

        try {
            CommandType commandType = parseCommandType(command);
            int taskNumber;
            String description;
            String[] parts;

            switch (commandType) {
            case LIST:
                if (tasks.isEmpty()) {
                    return "Your task list is empty.";
                } else {
                    return ui.showTasks(tasks.getList());
                }
            case BYE:
                return "Bye. Hope to see you again!";
            case MARK:
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= 0 || taskNumber > tasks.size()) {
                    throw new PixyExceptions("OOPS!!! The task number is out of range.");
                }
                tasks.get(taskNumber - 1).markAsDone(true);
                return "Task marked as done: " + tasks.get(taskNumber - 1).getDescription();
            case UNMARK:
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= 0 || taskNumber > tasks.size()) {
                    throw new PixyExceptions("OOPS!!! The task number is out of range.");
                }
                tasks.get(taskNumber - 1).markAsDone(false);
                return "Task unmarked: " + tasks.get(taskNumber - 1).getDescription();
            case DELETE:
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                if (taskNumber <= 0 || taskNumber > tasks.size()) {
                    throw new PixyExceptions("OOPS!!! The task number is out of range.");
                }
                Task task = tasks.get(taskNumber - 1);
                tasks.remove(task);
                return "Task deleted: " + task.getDescription() + ". You now have " + tasks.size() + " task(s).";
            case FIND:
                description = command.substring(5).trim();
                validateNoSpecialChars(description);
                if (description.isEmpty()) {
                    throw new PixyExceptions("OOPS!!! The search description cannot be empty.");
                }
                return ui.showMatchedTasks(tasks.find(description));
            case TODO:
                description = command.substring(5).trim();
                validateNoSpecialChars(description);
                if (description.isEmpty()) {
                    throw new PixyExceptions("OOPS!!! The description of a todo cannot be empty.");
                }
                Task todo = new ToDos(description);
                tasks.add(todo);
                return "Added new todo: " + todo.getDescription() + ". You now have " + tasks.size() + " task(s).";
            case DEADLINE:
                parts = command.substring(9).split(" /by");
                if (parts.length != 2 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty()) {
                    throw new PixyExceptions("OOPS!!! The deadline description is not in the correct format.");
                }
                try {
                    Task deadline = new Deadlines(parts[0].trim(), parts[1].trim());
                    tasks.add(deadline);
                    return "Added new deadline: " + deadline.getDescription() + " (by: " + parts[1].trim() + "). " +
                            "You now have " + tasks.size() + " task(s).";
                } catch (IllegalArgumentException e) {
                    return "Error: " + e.getMessage();
                }
            case EVENT:
                parts = command.substring(6).split(" /from | /to");
                if (parts.length != 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() ||
                        parts[2].trim().isEmpty()) {
                    throw new PixyExceptions("OOPS!!! The event description is not in the correct format.");
                }
                Task event = new Event(parts[0].trim(), parts[1].trim(), parts[2].trim());
                tasks.add(event);
                return "Added new event: " + event.getDescription() + " (from: " + parts[1].trim() + " to: " +
                        parts[2].trim() + "). You now have " + tasks.size() + " task(s).";
            default:
                return "Unknown command!";
            }
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid number for the task.";
        } catch (PixyExceptions e) {
            return e.getMessage();
        }
    }
}
