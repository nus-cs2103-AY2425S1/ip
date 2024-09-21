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
            throw new XBotException("I cannot add an event with empty description and empty dates >.<");
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
                    "Sorry... I do not understand your input... >_< \n"
                            + "could you use this format instead? \n"
                            + "event <task> /from <start time> /to <end time> "
                            + "(e.g. event Beach Day /from 9/4/2024 0900 /to 9/4/2024 1700)");
        }

        String taskDescription = parts[0].trim();
        String time = parts[1].trim();
        String[] timeParts = time.split("/to", 2);

        if (taskDescription.isEmpty()) {
            throw new XBotException("I cannot add an event with empty description >.<");
        }

        if (timeParts.length != 2) {
            throw new XBotException(
                    "Sorry... I do not understand your input... >_< \n"
                            + "could you use this format instead? \n"
                            + "event <task> /from <start time> /to <end time> "
                            + "(e.g. event Beach Day /from 9/4/2024 0900 /to 9/4/2024 1700)");
        }

        String from = timeParts[0].trim();
        String to = timeParts[1].trim();

        if (!Parser.isValidDateFormat(from) || !Parser.isValidDateFormat(to)) {
            throw new XBotException("Sorry...I cannot read this date input >_< \n"
                    + "you might want to try these date format instead :0\n"
                    + "D/M/YYYY (e.g. 9/4/2024) or D/M/YYYY HHMM (e.g. 9/4/2024 2359)");
        }

        Task newTask = new Event(taskDescription, from, to);
        list.add(newTask);
        return showEventAdded(newTask.toString(), list.size());
    }

    /**
     * Generates a success message indicating that a new event has been added to the task list.
     * @param newTaskString the string representation of the newly added task
     * @param listSize the new size of the task list after addition
     * @return a string message indicating the addition of the task and the updated list size
     */
    public String showEventAdded(String newTaskString, int listSize) {
        String output;
        output = ("Here comes another event! \n"
                + "I've added this task:\n");
        output = output + (newTaskString + "\n");
        output = output + ("And now you have " + listSize + " tasks in the list!! Jiayouu :D");
        return output;
    }
}

