public class DeleteCommand extends Command {
    private int deleteNumber;

    public DeleteCommand(int markNumber) {
        this.deleteNumber = deleteNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateMarkTaskNumber(deleteNumber);
            Commands.markTask(taskList, deleteNumber);
            ui.markMessage(taskList, deleteNumber);
            storage.saveTasks(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }
}
