import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public void add(Task task) {
        tasks.add(task);
    }

    public Task get(int index) {
        return tasks.get(index);
    }

    public Task remove(int index) {
        return tasks.remove(index);
    }

    public int size() {
        return tasks.size();
    }

    public boolean isEmpty() {
        return tasks.isEmpty();
    }

    public ArrayList<Task> getTasks() {
        return tasks;
    }

    public void findTasksByDate(LocalDate date, Ui ui) {
        ui.showFindTasksMessage(date);
        boolean found = false;
        for (Task task : tasks) {
            if (task instanceof Deadline && ((Deadline) task).isOnDate(date)) {
                System.out.println(task);
                found = true;
            } else if (task instanceof Event && ((Event) task).isOnDate(date)) {
                System.out.println(task);
                found = true;
            }
        }
        if (!found) {
            ui.showNoTasksFound();
        }
    }
}
