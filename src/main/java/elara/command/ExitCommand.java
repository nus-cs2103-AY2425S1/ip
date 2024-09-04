import elara.task.TaskList;

public class ExitCommand implements Command {
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        ui.showExitMessage();
        ui.closeScanner();
    }
}
