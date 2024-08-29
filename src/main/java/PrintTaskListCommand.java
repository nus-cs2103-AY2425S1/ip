public class PrintTaskListCommand extends Command {
    @Override
    protected void execute(TaskList taskList, Ui ui, Storage storage) throws TheBotFatherException {
        ui.printTaskList(taskList.getListDesc());
        return;
    }
}
