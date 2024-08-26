package GPT;
import java.time.LocalDateTime;

public class DeadlineCommand extends Command {
    private String description;
    private String by;

    public DeadlineCommand(String input) {
        String[] parts = input.substring(8).trim().split("/by");
        if (parts.length < 2 || parts[0].trim().isEmpty()) {
            this.description = null;
            this.by = null;
        } else {
            this.description = parts[0].trim();
            this.by = parts[1].trim();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (description == null || by == null) {
            ui.showError("Invalid command format for deadline.\nUsage: deadline [task description] /by [date/time]\nExample: deadline return book /by 2/12/2019 1800");
            return;
        }

        LocalDateTime byDateTime = Parser.parseDateTime(by);
        if (byDateTime != null) {
            Task newTask = new Deadline(description, byDateTime);
            taskList.addTask(newTask);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(newTask, taskList.getTasks().size());
        } else {
            ui.showError("The task was not added because of an invalid date.");
        }
    }
}
