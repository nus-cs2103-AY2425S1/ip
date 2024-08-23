public class AddCommand extends Command {
    private final Task task;
    AddCommand(Task task) {
        super();
        this.task = task;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws CheeseException {
        storage.add(task);
        tasks.add(task);
        ui.say(task, tasks, false);
    }
}
