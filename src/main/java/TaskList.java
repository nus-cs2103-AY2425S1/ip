import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<Task>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(
                "____________________________________\n" +
                "added: \"" + task.toString() + "\" to your TODO list!\n" +
                "____________________________________\n"
        );
    }

    @Override
    public String toString() {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________").append("\n").append("TODO:\n");

        for (Task task : tasks) {
            sb.append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }

        sb.append("_____________________________________").append("\n");
        return sb.toString();
    }
}
