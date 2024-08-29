public final class UncheckCommand extends Command {
    public UncheckCommand() {
        super("Right. I have marked this task on your list as not done:\n");
    }

    @Override
    public void execute(Ui ui, TaskList taskList) {
        int taskNumber = ui.promptTaskNumber(taskList);
        if (taskNumber != -1) {
            ui.printConfirmationMessage(taskList, getExecuteSuccessMessage() + taskList.uncheckTask(taskNumber));
        }
    }
}
