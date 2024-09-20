package chobo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private String description;
    private String by;

    public AddDeadlineCommand(String description, String by) throws InputException {
        this.description = description;
        this.by = by;
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("d-M-yyyy HHmm");
        try {
            LocalDateTime.parse(by.trim(), dateTimeFormatter);  // Test if the by format is correct
        } catch (DateTimeParseException e) {
            throw new InputException("deadline");
        }
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws InputException {
        Task deadline = new Deadline(description, false, by);
        taskList.addTask(deadline);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskAdded(deadline, taskList.getTotalTask());
    }
}
