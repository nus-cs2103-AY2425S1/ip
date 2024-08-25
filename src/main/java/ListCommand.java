public class ListCommand extends Command {
    @Override
    public void run(TaskList taskList, Storage storage, Ui ui) throws LamaException {
        ui.showListCommand(taskList);
    }

}
