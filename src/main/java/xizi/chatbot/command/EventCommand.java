package xizi.chatbot.command;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.regex.Matcher;

import xizi.chatbot.Parser;
import xizi.chatbot.Storage;
import xizi.chatbot.Ui;
import xizi.chatbot.XiziException;
import xizi.chatbot.task.Event;
import xizi.chatbot.task.Task;
import xizi.chatbot.task.TaskList;


/**
 * Represents a command to add an event task to the task list.
 * An event is defined by a description, a start time, and an end time.
 */
public class EventCommand implements Command {
    private final String taskDescription;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Constructs an EventCommand based on the user input.
     * Parses the input to extract the event description, start time, and end time.
     *
     * @param userInput The user input containing the event details.
     * @throws XiziException If the input format is invalid or if any field is empty.
     */
    public EventCommand(String userInput) throws XiziException {
        Matcher matcher = CommandType.EVENT.matcher(userInput);
        if (matcher.matches()) {
            taskDescription = matcher.group(1).trim();
            String fromStr = matcher.group(2).trim();
            String toStr = matcher.group(3).trim();
            if (taskDescription.isEmpty() || fromStr.isEmpty() || toStr.isEmpty()) {
                throw new XiziException("The description or time of an event cannot be empty.");
            }
            try {
                from = Parser.parseDateTime(fromStr);
                to = Parser.parseDateTime(toStr);
            } catch (DateTimeParseException e) {
                throw new XiziException("Invalid date/time format. Use the format: d/M/yyyy HHmm");
            }
        } else {
            throw new XiziException("Invalid event command format. Use: event <description> /from <start> /to <end>");
        }
    }

    /**
     * Executes the event command, adding a new event task to the task list.
     * The task is then saved to storage, and the user is notified of the successful addition.
     *
     * @param actions The task list where the event will be added.
     * @param storage The storage handler responsible for saving the new task.
     * @param ui      The user interface for interacting with the user.
     * @throws IOException   If an I/O error occurs during saving.
     * @throws XiziException If any validation error occurs.
     */
    @Override
    public void execute(TaskList actions, Storage storage, Ui ui) throws IOException, XiziException {
        Task task = new Event(taskDescription, from, to);
        // Check if the task already exists in the task list
        if (actions.getItems().contains(task)) {
            ui.showLine();
            ui.printMessage("This task already exists in the list:");
            ui.printMessage("  " + task);
            ui.showLine();
            return;
        }
        actions.addTask(task);
        storage.appendTask(task);
        ui.showLine();
        ui.printMessage("Got it. I've added this event:");
        ui.printMessage("  " + task);
        ui.printMessage("Now you have " + actions.getSize() + " tasks in the list.");
        ui.showLine();
    }
}

