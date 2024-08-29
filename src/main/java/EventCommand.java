import java.util.ArrayList;

public class EventCommand extends Command {
    private EventTask task;
    public EventCommand(EventTask task) {
        this.task = task;
    }
    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ArrayList<Task> list = tasks.getTasks();
        list.add(this.task);
        System.out.println("\nEl Primo:");
        System.out.println("Got it. I've added this task:");
        System.out.println(this.task);
        System.out.printf("Now you have %d tasks in the list.%n", list.size());
        storage.updateStorage(tasks);
    }
}
