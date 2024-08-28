public class DeleteCommand implements Command {
    private final TaskList tasks;
    private final Storage storage;
    private final Ui ui;
    private final String taskIndex;

    public DeleteCommand(TaskList tasks, Storage storage, Ui ui, String taskIndex) {
        this.tasks = tasks;
        this.storage = storage;
        this.ui = ui;
        this.taskIndex = taskIndex;
    }

    @Override
    public void execute() throws ArtsException {
        try {
            int index = Integer.parseInt(taskIndex) - 1;
            Task task = tasks.removeTask(index);
            storage.save(tasks.getTasks());
            ui.showMessage("Noted. I've removed this task:\n " + task +
                    "\nNow you have " + tasks.size() + " " + (tasks.size() == 1 ? "task" : "tasks") + " in the list.");
        } catch (NumberFormatException | IndexOutOfBoundsException e) {
            throw new ArtsException("Invalid task index.");
        }
    }
}

