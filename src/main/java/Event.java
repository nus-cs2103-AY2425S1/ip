import java.time.LocalDateTime;

public class Event extends Command {
    String description;
    LocalDateTime from;
    String to;
    public Event(String description, LocalDateTime from, String to) {
        this.description = description;
        this.from = from;
        this.to = to;
    }

    private static void taskAdded(TaskList list, Task t) {
        System.out.println("Got it. I've added this task:\n" + t);
        System.out.println("Now you have " + list.size() + (list.size() == 1 ? " task in the list." : " tasks in the list."));
    }
    @Override
    public void execute(TaskList list, Ui ui, Storage storage) {
        Task t = new EventTask(description, from, to);
        list.add(t);
        taskAdded(list, t);
    }

    @Override
    public boolean isExit() {
        return false;
    }
}
