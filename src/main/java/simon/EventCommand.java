package simon;

public class EventCommand implements Command{
    String name;
    String from;
    String to;

    public EventCommand(String name, String from, String to) {
        this.name = name;
        this.from = from;
        this.to = to;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        Events task = new Events(name, taskList.size(), from, to);
        taskList.add(task);
        ui.showTaskAdded(task, taskList.size());
        storage.saveToFile(taskList.toArr());

    }
}