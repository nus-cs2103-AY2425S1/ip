public class DeleteCommand extends Command {
    private int pos;

    public DeleteCommand(int pos) {
        this.pos = pos;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        if (pos > 0 && pos <= tasks.getLength()) {
//                        tasks.get(pos - 1).markDone();
//                        talk("Good job! Have marked as done: " + tasks.get(pos - 1));
            Task temp = tasks.deleteTask(pos - 1);
//                        curr--;
            ui.talk("Removed task: " + temp + "\nThere are "
                    + tasks.getLength() + " task(s) in the list.");
        } else {
            ui.showError("please choose a number between 1 and " + tasks.getLength());
        }
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
