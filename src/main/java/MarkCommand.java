import java.util.ArrayList;

public class MarkCommand extends Command {
    int index;
    public MarkCommand(int index) {
        this.index = index;
    }
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws PrimoException {
        ArrayList<Task> list = tasks.getTasks();
        if (index >= list.size() || index + 1 <= 0) {
            throw new PrimoException("Please select within the indexes of the tasklist!");
        }
        list.get(index).markAsDone();
        System.out.println("\nEl Primo:");
        System.out.println("Nice! I've marked this task as done:");
        System.out.println(list.get(index));
        storage.updateStorage(tasks);
    }
}
