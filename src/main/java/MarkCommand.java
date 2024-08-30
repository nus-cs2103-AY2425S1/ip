import java.io.IOException;

public class MarkCommand extends Command {
    private String[] commandParts;
    private boolean isMark;

    public MarkCommand(String[] commandParts, boolean isMark) {
        this.commandParts = commandParts;
        this.isMark = isMark;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SusanException, IOException {
        int taskIndex = Integer.parseInt(commandParts[1]) - 1;
        Task task = tasks.get(taskIndex);
        if (isMark) {
            task.markAsDone();
        } else {
            task.undoMark();
        }
        storage.load(tasks);
        ui.showTaskList(tasks);
    }
}