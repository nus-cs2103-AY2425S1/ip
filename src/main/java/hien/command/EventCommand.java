package hien.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import hien.exception.HienException;
import hien.main.Storage;
import hien.main.TaskList;
import hien.task.Event;
import hien.ui.UI;


/**
 * Represents a command to add an Event task to the task list.
 * The command takes user input in the format "event <description> /from <yyyy-MM-dd HHmm> /to <yyyy-MM-dd HHmm>"
 * and adds the event with the specified start and end times.
 */
public class EventCommand extends Command {
    private static final DateTimeFormatter INPUT_DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");
    private String input;

    /**
     * Constructs an EventCommand object.
     *
     * @param input  The user input specifying the event details.
     * @param isExit Specifies whether this command will terminate the application.
     */
    public EventCommand(String input, boolean isExit) {
        super(isExit);
        this.input = input;
    }

    /**
     * Adds an Event task to the task list.
     *
     * @param tasks   The task list to add the Event to.
     * @param input   The user input containing the event description, start, and end times.
     * @param storage The storage object used to save the task list.
     * @param ui      The UI object used to interact with the user.
     * @return        A message indicating the result of adding the event.
     * @throws HienException If the input format is incorrect or the date cannot be parsed.
     */
    private String addEvent(TaskList tasks, String input, Storage storage, UI ui) throws HienException {
        String[] parts = input.substring(5).split(" /from | /to ");
        String msg = "";
        if (parts.length == 3) {
            try {
                LocalDateTime startTime = LocalDateTime.parse(parts[1].trim(), INPUT_DATE_FORMAT);
                LocalDateTime endTime = LocalDateTime.parse(parts[2].trim(), INPUT_DATE_FORMAT);
                Event event = new Event(parts[0].trim(), startTime, endTime);
                tasks.addTask(event);
                storage.save(tasks);
                msg += ui.showMessage(" Got it. I've added this task:");
                msg += ui.showMessage("   " + event);
                msg += ui.showMessage(" Now you have " + tasks.size() + " tasks in the list.");
                return msg;
            } catch (DateTimeParseException e) {
                throw new HienException("☹ OOPS!!! The date format is incorrect. Please use: yyyy-MM-dd HHmm");
            }
        } else {
            throw new HienException("☹ OOPS!!! The event format is incorrect. "
                    + "Please use: event <description> /from <yyyy-MM-dd HHmm> "
                    + "/to <yyyy-MM-dd HHmm>");
        }
    }

    /**
     * Executes the command to add an Event task.
     *
     * @param tasks   The task list that the command operates on.
     * @param ui      The UI object that interacts with the user.
     * @param storage The storage object that handles saving and loading tasks.
     * @return        The result of the command execution as a string.
     * @throws HienException If the input format is invalid or date parsing fails.
     */
    @Override
    public String execute(TaskList tasks, UI ui, Storage storage) throws HienException {
        return addEvent(tasks, input, storage, ui);
    }
}