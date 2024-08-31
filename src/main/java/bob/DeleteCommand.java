package bob;

import java.io.IOException;

public class DeleteCommand extends Command {
    private int taskIndex;
    /**
     * Creates a command object that operates on task i of the tasklist
     * @param taskIndex
     */
    public DeleteCommand(int taskIndex) {
        this.taskIndex = taskIndex;
    }
    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        try {
            Task task = tasks.remove(taskIndex);
            storage.save(tasks);
            ui.show(String.format("""
                                Oof. I have removed the requested task:
                                \t%s
                                Now you have %s tasks in the list""", task, tasks.size()));
        } catch (IndexOutOfBoundsException e) {
            ui.show("Hm, you don't seem to have that task");
        } catch (IOException e) {
            ui.show("I can't remember that for some reason T T");
        }
    }
}