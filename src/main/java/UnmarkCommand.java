public class UnmarkCommand extends Command {
    private final String args;

    public UnmarkCommand(String args) {
        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws MorganaException {
        int index = Parser.parseTaskIndex(args, tasks);
        Task task = tasks.get(index);
        task.markAsDone(false);
        ui.showToUser(
                "OK, I've marked this task as not done yet:",
                "%d. %s".formatted(index + 1, task));
        storage.save(tasks);
    }
}
