public class DeleteCommand extends Command {

    private TaskList tasks;
    private int index;

    public DeleteCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            Ui.printText("Done, removed that task for you.\n" + tasks.get(index).toString());
            tasks.remove(index);
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
