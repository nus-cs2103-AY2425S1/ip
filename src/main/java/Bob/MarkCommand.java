package Bob;

import java.io.IOException;

public class MarkCommand extends Command {
    private int taskIndex;

    /**
     * Creates a command object that operates on task i of the tasklist
     * @param taskIndex
     */
    public MarkCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.getTask(taskIndex);
            task.mark();
            storage.save(tasks);
            ui.show(String.format("Nice! I've marked this task as done:\n\t%s", task));
        } catch (IndexOutOfBoundsException e) {
            ui.show("Nice try but there's no such task.");
        } catch (IOException e){
            ui.show("I can't remember that for some reason T T");
        }
    }
}
