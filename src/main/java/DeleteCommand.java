public class DeleteCommand extends Command{
    private int taskNumber;
    public DeleteCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws LightException {
            ui.showMessage("Noted. I've removed this task:\n" + tasks.get(taskNumber) + "\nNow you have " + (tasks.size() - 1)  + " tasks in the list.");
            tasks.remove(taskNumber);
            storage.write(TaskList.arrayToNumberedString(tasks));
    }
}
