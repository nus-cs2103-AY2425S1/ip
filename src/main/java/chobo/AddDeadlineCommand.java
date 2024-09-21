package chobo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * The type Add deadline command.
 */
public class AddDeadlineCommand extends Command {
    private String taskName;
    private String by;

    /**
     * Instantiates a new Add deadline command.
     *
     * @param taskName Name of deadline.
     * @param by   Date and time for deadline.
     * @throws InputException Input exception if input does not follow format.
     */
    public AddDeadlineCommand(String taskName, String by) throws InputException {
        this.taskName = taskName;
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
        Task deadline = new Deadline(taskName, false, by);
        taskList.addTask(deadline);
        storage.saveTasks(taskList.getTasks());
        return ui.showTaskAdded(deadline, taskList.getTotalTask());
    }
}
