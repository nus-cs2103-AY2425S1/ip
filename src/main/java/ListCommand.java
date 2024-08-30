import java.util.ArrayList;

public class ListCommand extends UserCommand {
    @Override
    public void execute(String userInput, Ui ui, Storage storage, TaskList taskList) throws LevelHundredException {
        ArrayList<Task> tasks = taskList.getTaskList();
        ui.printTasks(tasks);
    }
}    