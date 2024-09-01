import java.io.IOException;

class UnmarkCommand extends Command {
    private int idx;

    public UnmarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).unmark();
            ui.showUnmark(tasks.getTask(idx - 1));
            //ui.display("Marked as not done : ");
            //ui.display(tasks.getTask(idx - 1).toString());
        } catch (IndexOutOfBoundsException e) {
            ui.showError("OOPS!!! Task index is out of bounds.");
        }

        try {
            storage.save(tasks.getTaskList());
        } catch (IOException e) {
            ui.showError("We cannot save the tasks: " + e.getMessage());
        }
    }
}
