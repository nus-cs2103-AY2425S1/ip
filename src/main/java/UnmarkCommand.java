public class UnmarkCommand implements Command {

    public final String taskNumber;
    public UnmarkCommand(String taskNumber) {
        this.taskNumber = taskNumber;
    }
    public void execute(Storage storage, TaskList tasks, Ui ui) throws DownyException {
        try {
            int taskNum = Integer.parseInt(this.taskNumber);
            tasks.unmarkTaskInList(taskNum);
            Task t = tasks.getTask(taskNum - 1);
            storage.markIncomplete(t);
            ui.displayIncompleteTask(t);
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


