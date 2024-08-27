public class TodoCommand implements Command {

    private final String taskDescription;

    public TodoCommand(String taskDescription) {
        this.taskDescription = taskDescription;

    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        try {
            Todo t = tasks.addTodo(taskDescription);
            storage.writeTodoToFile(t);
            ui.displayTaskAdded(t, tasks.getSize());
        } catch (NumberFormatException e) {
            throw new InvalidFormatException("Task number has to be a positive integer.");
        } catch (IndexOutOfBoundsException e) {
            throw new IllegalArgumentException("Task number does not exist.");
        }
    }
    public boolean isExit() {
        return false;
    }
}
