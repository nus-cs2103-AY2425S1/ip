package chobo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddEventCommand extends Command {
    private String description;
    private String unformattedFrom;
    private String unformattedTo;
//    private LocalDateTime from;
//    private LocalDateTime to;

    public AddEventCommand(String description, String from, String to) throws InputException{
        this.description = description;
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
        Task event = new Event(description, false, unformattedFrom, unformattedTo);
        taskList.addTask(event);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskAdded(event, taskList.getTotalTask());
    }
}
