public class AddCommand extends Command {
    private final Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        tasks.addTask(task);

        String message = String.format("added: %s\nAnd another one; %d out of %d tasks to complete...",
                task,
                tasks.getIncompleteCount(),
                tasks.getCount());
        ui.printOutput(message);

        storage.saveNewTask(task, ui);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
