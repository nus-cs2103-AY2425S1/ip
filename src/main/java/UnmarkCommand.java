public class UnmarkCommand extends Command {
    public UnmarkCommand(String input) {
        super(input);
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JeffException {
        // Get the task from taskList
        Task targetTask = tasks.getTask(this.getInput(), "unmark ");

        // Check if the task has been done or not
        if (targetTask.isDone()) {
            // Unmark the task
            targetTask.markAsNotDone();

            ui.printText("OK, I've marked this task as not done yet:\n   " + targetTask.toString());

        } else {
            // Tell the user that it already is not done
            throw new JeffException("This task has already been marked as not done yet!");

        }
    }
}
