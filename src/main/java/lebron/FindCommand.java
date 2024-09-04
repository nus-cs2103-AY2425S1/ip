package lebron;

public class FindCommand extends Command {

    public final String keyword;

    public FindCommand(String keyword) {
        this.keyword = keyword;
    }

    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) throws LeBronException {
        return ui.showMatchingTasks(taskList, keyword);
    }

}
