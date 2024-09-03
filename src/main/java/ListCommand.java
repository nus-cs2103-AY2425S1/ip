public class ListCommand extends Command {
    @Override
    public void executeCommand(TaskList taskList, Ui ui, Storage storage) {
        taskList.printTask();
    }
}
