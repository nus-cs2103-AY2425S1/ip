import java.io.IOException;

class DeleteCommand extends Command {
    private int idx;

    public DeleteCommand(int idx) {
        this.idx = idx;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task deleted = tasks.getTask(idx - 1);
            tasks.deleteTask(idx - 1);
            ui.showDelete(deleted, tasks.size());
            //ui.display("Noted. I've removed this task :");
            //ui.display(deleted.toString());
            //ui.display(String.format("Now you have %d task in the list.", tasks.size()));
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
