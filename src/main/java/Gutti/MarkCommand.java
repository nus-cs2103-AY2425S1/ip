package Gutti;

public class MarkCommand extends Command {
    private final int taskIndex;

    public MarkCommand(String commandString) throws GuttiException {
        try {
            this.taskIndex = Integer.parseInt(commandString) - 1;
        } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
            throw new GuttiException("Invalid index format for mark command.");
        }
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws GuttiException {
        try {
            Task task = tasks.getTasks().get(taskIndex);
            task.markAsDone();
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