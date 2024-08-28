import java.io.IOException;

public class DeleteCommand implements Command {
    private String remaining;

    public DeleteCommand(String remaining) {
        this.remaining = remaining;
    }

    @Override
    public boolean execute(TaskList taskList, Ui ui, Storage storage) throws IOException {
        int index = Integer.parseInt(remaining);
        try {
            taskList.deleteTask(index, ui);
            storage.save(taskList);
        } catch (InvalidTaskException | IOException e) {
            ui.displayString(e.getMessage());
        }
        return true;
    }
}
