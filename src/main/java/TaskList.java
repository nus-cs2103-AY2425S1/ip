import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> list;

    public TaskList() {
        this.list = new ArrayList<>();
    }

    public TaskList(List<Task> list) throws JudeException {
        this.list = list;
    }

    public void addTask(Task task) {
        list.add(task);
    }

    public void deleteTask(int index) {
        list.remove(index);
    }

    public void print() {
        for (int i = 0; i < list.size(); i++) {
            System.out.printf("%d. %s\n", (i + 1), list.get(i).toStringDetails());
        }
    }

    public String toFileFormat() {
        String text = "";
        for (Task task : list) {
            text += task.toFileFormat() + "\n";
        }
        return text;
    }
}
