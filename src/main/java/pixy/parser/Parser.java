package pixy.parser;

import pixy.PixyExceptions;
import pixy.tasks.Deadlines;
import pixy.tasks.Event;
import pixy.tasks.Task;
import pixy.tasks.TaskList;
import pixy.tasks.ToDos;
import pixy.ui.Ui;

/**
 * Parses the user commands and returns the command type.
 */
public class Parser {

    /**
     * Determines the command type from the user's input.
     *
     * @param command User inputted command
     * @return CommandType The type of the command
     */
    public CommandType parseCommandType(String command) {
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
                ui.showGoodbyeMessage();
                return "Bye. See you again!";
            case MARK:
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                tasks.get(taskNumber - 1).markAsDone(true);
                return "Task marked as done: " + tasks.get(taskNumber - 1).getDescription();
            case UNMARK:
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                tasks.get(taskNumber - 1).markAsDone(false);
                return "Task unmarked: " + tasks.get(taskNumber - 1).getDescription();
            case DELETE:
                taskNumber = Integer.parseInt(command.split(" ")[1]);
                Task task = tasks.get(taskNumber - 1);
                tasks.remove(task);
                return "Task deleted: " + task.getDescription() + ". You now have " + tasks.size() + " task(s).";
            case FIND:
                description = command.substring(5);
                return ui.showMatchedTasks(tasks.find(description));
            case TODO:
                description = command.substring(5);
                if (description.isEmpty()) {
                    throw new PixyExceptions("OOPS!!! The description of a todo cannot be empty.");
                }
                Task todo = new ToDos(description);
                tasks.add(todo);
                return "Added new todo: " + todo.getDescription() + ". You now have " + tasks.size() + " task(s).";
            case DEADLINE:
                parts = command.substring(9).split(" /by");
                if (parts.length != 2) {
                    throw new PixyExceptions("OOPS!!! The description of a deadline is not in the correct format.");
                }
                Task deadline = new Deadlines(parts[0], parts[1]);
                tasks.add(deadline);
                return "Added new deadline: " + deadline.getDescription() + " (by:" + parts[1] + "). You now have " + tasks.size() + " task(s).";
            case EVENT:
                parts = command.substring(6).split(" /from | /to");
                if (parts.length != 3) {
                    throw new PixyExceptions("OOPS!!! The description of an event is not in the correct format.");
                }
                Task event = new Event(parts[0], parts[1], parts[2]);
                tasks.add(event);
                return "Added new event: " + event.getDescription() + " (from: " + parts[1] + " to:" + parts[2] + "). You now have " + tasks.size() + " task(s).";
            default:
                return "Unknown command!";
            }
        } catch (NumberFormatException e) {
            return "OOPS!!! Please provide a valid number for the task.";
        } catch (PixyExceptions e) {
            return "Error: " + e.getMessage();
        }
    }
}
