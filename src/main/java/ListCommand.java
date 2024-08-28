
public class ListCommand extends Command {

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        ui.showTaskList(taskList);
    }
    
}
