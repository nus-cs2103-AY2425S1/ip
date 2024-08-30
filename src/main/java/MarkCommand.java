public class MarkCommand implements Command {
    private final String fullCommand;

    public MarkCommand(String fullCommand) {
        this.fullCommand = fullCommand;
    }

    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        int taskIndex = Integer.parseInt(fullCommand.split(" ")[1]) - 1;
        list.markTask(taskIndex);
        ui.showMarkedTaskMessage(list.getTask(taskIndex));
        storage.saveTasks(list);
    }
}
