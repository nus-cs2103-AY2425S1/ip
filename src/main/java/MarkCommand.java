public class MarkCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String taskIndex;

    public MarkCommand(TaskList tasks, Storage storage, Ui ui, String taskIndex) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task task = tasks.getTask(index);
            task.markAsDone();
            storage.save(tasks.getTasks());
            ui.showMessage("Nice! I've marked this task as done:\n " + task);
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ArtsException("Invalid task index.");
        }
    }
}
