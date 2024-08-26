import java.time.format.DateTimeParseException;
import java.util.List;
import java.util.ArrayList;

public class TaskList {

    private List<Task> items;

    public TaskList() {
        this.items = new ArrayList<>();
    }

    public TaskList(List<Task> items) {
        this.items = items;
    }

    public void add(Task task) {
        this.items.add(task);
    }

    public String list() {
        String message = "     Here are the tasks in your list:\n";
        for (int i = 1; i <= this.items.size(); i++) {
            message += String.format(
                "     %d.%s" + (i == this.items.size() ? "" : "\n"),
                i, this.items.get(i - 1)
            );
        }
        return message;
    }

    public String mark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     Nice! I've marked this task as done:\n";
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).markAsDone();
        message += String.format("       %s", this.items.get(itemIdx - 1));
        return message;
    }

    public String unmark(int itemIdx) throws IndexOutOfBoundsException {
        String message = "     OK, I've marked this task as not done yet:\n";
        if (itemIdx > this.items.size()) {
            throw new IndexOutOfBoundsException();
        }
        this.items.get(itemIdx - 1).markAsUndone();
        message += String.format("       %s", this.items.get(itemIdx - 1));
        return message;
    }

    public String delete(int itemIdx) throws IndexOutOfBoundsException {
        Task deletedTask = this.items.remove(itemIdx - 1);
        String message = "     Noted. I've removed this task:\n" +
                String.format("       %s\n", deletedTask.toString()) +
                String.format("     Now you have %d %s in the list.",
                    this.items.size(), (this.items.size() == 1 ? "task" : "tasks"));
        return message;
    }

    public String getTasksForSaving() {
        String dataString = "";
        for (Task task : this.items) {
            dataString += task.getTaskForSaving();
        }
        return dataString;
    }

    public int getNumItems() {
        return this.items.size();
    }
}
