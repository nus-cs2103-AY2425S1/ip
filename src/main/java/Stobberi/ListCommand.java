package Stobberi;

public class ListCommand extends Command {
    private TaskList taskList;
    public ListCommand(TaskList taskList) {
        this.taskList = taskList;
    }

    @Override
    public void execute() {
        taskList.displayList();
    }
}