import java.util.ArrayList;

public class DeleteCommand extends Command {
    int index;
    public DeleteCommand(int index) {
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
        System.out.println("\nEl Primo:");
        System.out.println("Noted. I've removed this task:");
        System.out.println(list.get(index));
        list.remove(index);
        storage.updateStorage(tasks);
    }
}
