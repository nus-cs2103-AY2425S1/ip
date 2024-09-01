import java.io.IOException;

public class EditTaskCommand extends Command{
    protected boolean isDone;
    protected int index;

    public EditTaskCommand(Boolean isDone, int index) {
        this.isDone = isDone;
        this.index = index;
    }

    @Override
    public void execute(TaskList taskLists, Ui ui, Storage storage) {
        try {
            Task task = taskLists.getTask(index);
            if (isDone) {
                task.editStatus();
                ui.markTask(task);
            } else {
                ui.unmarkTask(task);
            }
            storage.saveTask(taskLists);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("Task List index is out of bounds!!");
        } catch (IOException e) {

        }

    }
    @Override
    public boolean isBye() {
        return false;
    }
}