public class PrintCommand extends Command {
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws DeltaException {
        if (tasks.getSize() == 0) {
            throw new DeltaException("There are no tasks in your list.");
        } else {
            ui.showCommand("Here are the tasks in your list:");
        }
        for (int i = 0; i < tasks.getSize(); i++) {
            ui.showCommand(String.format("%d.%s", i + 1, tasks.getTask(i)));
        }
    }
}
