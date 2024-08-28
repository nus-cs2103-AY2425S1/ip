package lebron;

public class FindCommand extends Command {

    public final String keyword;
    
    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public void execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        ui.showMatchingTasks(taskList, keyword);
    }
    
}
