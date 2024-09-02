public class ListCommand extends Command {
    ListCommand() {}

    void execute(TaskList taskList, Ui ui, Storage storage) {
        taskList.listTasks();
    }
}
