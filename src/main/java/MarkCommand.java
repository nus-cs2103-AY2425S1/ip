import java.io.IOException;

class MarkCommand extends Command {
    private int idx;

    public MarkCommand(int idx) {
        this.idx = idx;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            tasks.getTask(idx - 1).mark();
            ui.showMark(tasks.getTask(idx - 1));
            //ui.display("Marked as done : ");
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
