public class DeleteCommand implements Command {

    public final String taskNumber;

    public DeleteCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }

    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        try {
            int taskNum = Integer.parseInt(this.taskNumber);
            Task t = tasks.deleteTaskInList(taskNum);
            storage.deleteTask(t);
            ui.displayDeletedTask(t);
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
