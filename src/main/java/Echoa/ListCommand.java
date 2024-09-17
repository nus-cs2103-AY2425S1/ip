package echoa;

public class ListCommand extends Command {

    public ListCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }
    public void execute(String line) {
        // do nothing
    }

    public String getMessage() {
        return ui.getListOfTasksMessage(taskList);
    }
}
