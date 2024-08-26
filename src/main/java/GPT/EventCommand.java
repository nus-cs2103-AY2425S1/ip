package GPT;
import java.time.LocalDateTime;

public class EventCommand extends Command {
    private String description;
    private String from;
    private String to;

    public EventCommand(String input) {
        String[] parts = input.substring(5).trim().split("/from|/to");
        if (parts.length < 3 || parts[0].trim().isEmpty() || parts[1].trim().isEmpty() || parts[2].trim().isEmpty()) {
            this.description = null;
            this.from = null;
            this.to = null;
        } else {
            this.description = parts[0].trim();
            this.from = parts[1].trim();
            this.to = parts[2].trim();
        }
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        if (description == null || from == null || to == null) {
            ui.showError("Invalid command format for event.\nUsage: event [task description] /from [start date/time] /to [end date/time]\nExample: event project meeting /from 2/12/2019 1400 /to 2/12/2019 1600");
            return;
        }

        LocalDateTime fromDateTime = Parser.parseDateTime(from);
        LocalDateTime toDateTime = Parser.parseDateTime(to);

        if (fromDateTime != null && toDateTime != null) {
            Task newTask = new Event(description, fromDateTime, toDateTime);
            taskList.addTask(newTask);
            storage.saveTasks(taskList.getTasks());
            ui.showTaskAdded(newTask, taskList.getTasks().size());
        } else {
            ui.showError("The task was not added because of an invalid date or time.");
        }
    }
}
