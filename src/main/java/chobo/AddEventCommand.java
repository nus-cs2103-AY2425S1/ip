package chobo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Add event command.
 */
public class AddEventCommand extends Command {
    private String taskName;
    private String unformattedFrom;
    private String unformattedTo;

    /**
     * Instantiates a new Add event command.
     *
     * @param taskName Name of the task.
     * @param from     Start date and time of the event.
     * @param to       End date and time of the event.
     * @throws InputException Input exception if input does not follow format.
     */
    public AddEventCommand(String taskName, String from, String to) throws InputException {
        this.taskName = taskName;
        this.unformattedFrom = from;
        this.unformattedTo = to;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        try {
            LocalDateTime.parse(from.trim(), dateTimeFormatter);  // Parse the start date
            LocalDateTime.parse(to.trim(), dateTimeFormatter);      // Parse the end date
        } catch (DateTimeParseException e) {
            throw new InputException("event");
        }
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task event = new Event(taskName, false, unformattedFrom, unformattedTo);
        taskList.addTask(event);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskAdded(event, taskList.getTotalTask());
    }
}
