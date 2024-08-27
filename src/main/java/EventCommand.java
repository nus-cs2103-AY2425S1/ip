public class EventCommand extends Command {
    private Event eventTask;
    public EventCommand(Event eventTask) {
        this.eventTask = eventTask;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Commands.addTask(taskList, eventTask);
        ui.addTaskMessage(taskList, eventTask);
        storage.saveTasks(taskList);
    }
}
