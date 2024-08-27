public class UnmarkCommand extends Command {
    private int unmarkNumber;

    public UnmarkCommand(int unmarkNumber) {
        this.unmarkNumber = unmarkNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateUnmarkTaskNumber(unmarkNumber);
            Commands.unmarkTask(taskList, unmarkNumber);
            ui.unmarkMessage(taskList, unmarkNumber);
            storage.saveTasks(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }
}
