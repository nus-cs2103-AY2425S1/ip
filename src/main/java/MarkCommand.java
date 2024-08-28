public class MarkCommand extends Command {

    private TaskList tasks;
    private int index;

    public MarkCommand(TaskList tasks, int index) {
        this.tasks = tasks;
        this.index = index;
    }

    @Override
    public void execute() {
        try {
            tasks.get(index).markAsDone();
            Ui.printText("Marked that as done for you.\n" + tasks.get(index).toString());
        } catch (IndexOutOfBoundsException e) {
            throw new InvalidTaskException("ERROR! Task not found.");
        }
    }

    @Override
    public boolean isTerminated() {
        return false;
    }
}
