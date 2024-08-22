import java.time.LocalDate;
import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks;
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    public void deleteTask(int i) {
        tasks.remove(i);
    }

    public void markTask(int i) {
        Task toMark = tasks.get(i);
        toMark.mark();
    }

    public void unmarkTask(int i) {
        Task toUnmark = tasks.get(i);
        toUnmark.unmark();
    }

    public void addTodo(Todo t) {
        tasks.add(t);
    }

    public void addDeadline(Deadline d) {
        tasks.add(d);
    }

    public void addEvent(Event e) {
       tasks.add(e);
    }

    public int size() {
        return tasks.size();
    }

    public String stringAt(int i) {
        return tasks.get(i).toString();
    }

    public Task get(int i) {
        return tasks.get(i);
    }

    public String toString() {
        String res = "";
        for (int i = 0; i < tasks.size(); i++) {
            int listIndex = i + 1;
            res += listIndex + ". " + tasks.get(i).toString() + "\n";
        }

        return res;
    }
}
