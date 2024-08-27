public class UnmarkCommand extends Command {
    private final int index;

    public UnmarkCommand(int index) {
        this.index = index - 1;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws EkudException {
        Task task = tasks.getTask(index);
        String previousSaveState = task.getSaveTaskString();
        task.markAsUndone();
        String message = String.format("""
                        Oh ho ho, did you perhaps forget something?
                        It's OK, I already noted down your incompetence...
                          %s
                        Tsk Tsk... Back to %d out of %d incomplete tasks you go!""",
                tasks.getTask(index),
                tasks.getIncompleteCount(),
                tasks.getCount());
        ui.printOutput(message);

        // update data file
        storage.updateTaskState(task, previousSaveState, ui);

    }

    @Override
    public boolean isExit() {
        return false;
    }
}
