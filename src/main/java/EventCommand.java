import java.time.LocalDateTime;

public class EventCommand extends Command {
    private Event eventTask;
    public EventCommand(String descrption, LocalDateTime start, LocalDateTime end) {
        this.eventTask = new Event(descrption.trim(), start, end);
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.add(this.eventTask);
        ui.addTaskMessage(taskList, eventTask);
        storage.saveTasks(taskList);
    }
}
