package chobo;

/**
 * Represents the command which lists tasks.
 */
public class ListCommand extends Command {
    @Override
    public String execute(TaskList taskList, Ui ui, Storage storage) {
        return ui.showTaskList(taskList.getTasks());
    }
}
