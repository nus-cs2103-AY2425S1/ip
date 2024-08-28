public class DeleteCommand extends Command {
    private int deleteNumber;

    public DeleteCommand(int markNumber) {
        this.deleteNumber = deleteNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateMarkTaskNumber(this.deleteNumber);
            taskList.delete(this.deleteNumber);
            ui.markMessage(taskList, this.deleteNumber);
            storage.saveTasks(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }
}
