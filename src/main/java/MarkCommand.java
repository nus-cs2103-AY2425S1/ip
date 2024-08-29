public class MarkCommand extends Command {
    private int index;

    public MarkCommand(int index) {
        this.index = index - 1;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SageException {
        try {
            Task task = tasks.get(index);
            task.markAsDone();
            ui.showMessage("Great Job! I have marked this task as done:\n" + task);
            storage.saveTasks(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new SageException("Oh no! This task number is invalid :(");
        }
    }
}
