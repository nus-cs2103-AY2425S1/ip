public class ListCommand extends Command {
    @Override
    public String execute() {
        return taskList.listTasks();
    }
}
