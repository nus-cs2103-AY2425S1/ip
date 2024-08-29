public class MarkCommand extends Command {

    private int index;

    public MarkCommand(int index) {
        this.index = index;
    }

    public void execute(TaskList tasks,Ui ui, Storage storage) {
        if (index >= tasks.getLength() || index < 0) {
            ui.showTaskNotFoundError();
            return;
        }
        tasks.getTask(index).markCompleted();
        storage.overwriteFile(tasks);
        System.out.println("    Good job! I've marked this task as done:");
        System.out.println("       " + tasks.getTask(index).toString());
    }
}
