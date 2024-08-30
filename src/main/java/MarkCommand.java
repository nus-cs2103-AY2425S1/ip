public class MarkCommand extends Command {
    private int pos;

    public MarkCommand(int pos) {
        this.pos = pos;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (pos > 0 && pos <= tasks.getLength()) {
//                        tasks.get(pos - 1).markDone();
            tasks.markTask(pos - 1);
            ui.talk("Good job! Have marked as done: " + tasks.getTaskString(pos - 1));
        } else {
            ui.showError("please choose a number between 1 and " + tasks.getLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
