package echoa;

public class FindCommand extends Command {

    private TaskList newTaskList;

    public FindCommand(Ui ui, Parser parser, TaskList taskList, Storage storage) {
        super(ui, parser, taskList, storage);
    }
    public void execute(String line) {
        newTaskList = parser.parseFindTask(taskList, line);
    }

    public String getMessage() {
        return ui.getListOfTasksMessage(newTaskList);
    }
}
