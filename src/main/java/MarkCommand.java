public class MarkCommand extends Command {
    private int markNumber;

    public MarkCommand(int markNumber) {
        this.markNumber = markNumber;
    }
    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) {
        try {
            taskList.validateMarkTaskNumber(this.markNumber);
            Task currTask = taskList.get(this.markNumber -1);
            currTask.setIsDone(true);
            ui.markMessage(taskList, markNumber);
            storage.saveTasks(taskList);
        } catch (JustbotException e) {
            ui.getJustBotExceptionMessage(e);
        }
    }
}
