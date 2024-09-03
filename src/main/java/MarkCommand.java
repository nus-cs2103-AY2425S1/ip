import java.io.IOException;

public class MarkCommand extends Command {
    private int index;

    public MarkCommand(String[] inputParts) throws VinegarException {
        Validator.validateParts(inputParts, 1, "Please specify which task to mark.");
        this.index = Integer.parseInt(inputParts[1]) - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws VinegarException, IOException {
        tasks.getTask(index).markAsDone();
        ui.showMarked(tasks.getTask(index));
        storage.save(tasks.getTasks());
    }
}
