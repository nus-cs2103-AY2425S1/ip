package xbot.command;

import xbot.TaskList;
import xbot.exception.XBotException;
import xbot.parser.Parser;
import xbot.storage.Storage;
import xbot.task.Event;
import xbot.task.Task;
import xbot.ui.Ui;

/**
 * Handles the "event" command.
 */
public class EventCommand extends AddCommand {
    @Override
    public String execute(TaskList list, Ui ui, Storage storage, String rest) throws XBotException {
        if (rest.trim().isEmpty()) {
            throw new XBotException("The description of the event cannot be empty!");
        }
        String output = addEvent(list, rest);
        storage.saveTask(list);
        return output;
    }

    /**
     * Adds a new Event task to the task list.
     * The task description should be followed by /to and /from keyword specifying the time period.
     *
     * @param rest The description and time period of the task.
     * @throws XBotException If the input format is invalid or the dates are in an incorrect format.
     */
    public String addEvent(TaskList list, String rest) throws XBotException {
        String[] parts = rest.split("/from", 2);

        if (parts.length != 2) {
            throw new XBotException(
                    "Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }

        String taskDescription = parts[0].trim();
        String time = parts[1].trim();
        String[] timeParts = time.split("/to", 2);

        if (timeParts.length != 2) {
            throw new XBotException(
                    "Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (!Parser.isValidDateFormat(from) || !Parser.isValidDateFormat(to)) {
            throw new XBotException("Invalid date input format. Please use the format: D/M/YYYY");
        }

        Task newTask = new Event(taskDescription, from, to);
        list.add(newTask);
        return showEventAdded(newTask.toString(), list.size());
    }

    public String getTaskDescription(String rest) throws XBotException {
        String[] parts = rest.split("/from", 2);

        if (parts.length != 2) {
            throw new XBotException(
                    "Invalid input format. Please use the format: 'event <task> /from <start time> /to <end time>'");
        }
        return parts[0].trim();
    }

    public String showEventAdded(String newTaskString, int listSize) {
        String output;
        output = ("Got it. I've added this task:\n");
        output = output + (newTaskString + "\n");
        output = output + ("Now you have " + listSize + " tasks in the list.");
        return output;
    }
}

