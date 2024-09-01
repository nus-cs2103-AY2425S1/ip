import java.io.IOException;

public class DeleteCommand extends Command {
    int deletedIndex;

    public DeleteCommand(int deletedIndex) {
        this.deletedIndex = deletedIndex;
    }

    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        try {
            Task deletedTask = taskLists.removeTask(deletedIndex);
            ui.deleteTask(deletedTask, taskLists.size());
            storage.saveTask(taskLists);
        } catch (IOException e) {

        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task List index is out of bounds!!");
        }
    }

    @Override
    public boolean isBye() {
        return false;
    }
}
