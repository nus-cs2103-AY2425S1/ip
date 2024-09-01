public class DeleteTaskCommand extends Command{

    private final int taskNumber;

    public DeleteTaskCommand(int taskNumber) {
        this.taskNumber = taskNumber;
    }

    @Override
    public void execute(Tasklist tasklist, Ui ui, Storage storage) throws TayooException {

        String taskStr = tasklist.getTaskStr(this.taskNumber);

        if (tasklist.deleteTask(this.taskNumber)) {
            storage.deleteTxt(this.taskNumber);
            String toPrint = "Noted. I've removed this task:\n" + taskStr;

            int tasklistSize = tasklist.getSize();
            if (tasklistSize > 1) {
                toPrint += "\n Now you have " + tasklistSize + " tasks in your list";
            } else {
                toPrint += "\n Now you have " + tasklistSize + " task in your list";
            }
            ui.printText(toPrint);
        } else {
            ui.printText("You have too many tasks in your tasklist!");
        }


    }

    @Override
    public boolean isExit() {
        return false;
    }
}
