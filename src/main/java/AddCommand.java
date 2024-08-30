import java.io.IOException;

public class AddCommand extends Command {

    private Task task;

    public AddCommand(Task task) {
        this.task = task;
    }

    @Override
    public void executeCmd(LuffyUI ui, Storage taskStorage, TaskList taskList) {
        taskList.addTask(this.task);
        try {
            taskStorage.saveToFile(taskList);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
        ui.showAddedTask(this.task, taskList);
    }

}
