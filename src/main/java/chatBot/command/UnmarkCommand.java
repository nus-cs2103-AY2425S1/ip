public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int i) {
        this.index = i;
    }

    void execute(TaskList taskList, Ui ui, Storage storage) {
        if (this.index > taskList.size()) {
            System.out.println("index out of range");
            return;
        }
        Task t = taskList.getTask(this.index).markAsNotDone();
        System.out.println("I've marked as done:\n" + t);
    }
}