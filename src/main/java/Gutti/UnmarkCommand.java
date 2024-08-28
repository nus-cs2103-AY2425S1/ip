package Gutti;

public class UnmarkCommand extends Command {
    private final int taskIndex;

    public UnmarkCommand(String commandString) throws GuttiException {
        try {
            this.taskIndex = Integer.parseInt(commandString) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new GuttiException("Invalid index format for unmark command.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        try {
            Task task = tasks.getTasks().get(taskIndex);
            task.unmark();
            storage.saveTasksToFile(tasks.getTasks());
            ui.showTaskList(tasks);
        } catch (IndexOutOfBoundsException e) {
            throw new GuttiException("Task index out of range.");
        }
    }
    @Override
    public boolean isExit() {
        return false;
    }
}