import java.io.IOException;

public class UnmarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a command object that operates on task i of the tasklist
     * @param taskIndex
     */
    public UnmarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(taskIndex);
            task.unmark();
            storage.save(tasks);
            ui.show(String.format("Oh well, this task has been marked undone:\n\t%s", task));
        } catch (IndexOutOfBoundsException e) {
            ui.show("There's no such task!");
        } catch (IOException e){
            ui.show("I can't remember that for some reason T T");
        }
    }
}