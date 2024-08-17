import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private List<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public void addTask(Task task) {
        tasks.add(task);
        System.out.println(
                "____________________________________\n" +
                "Got it. I've added this task:\n" +
                task.toString() + "\n" +
                "____________________________________\n" +
                "Now you have " + tasks.size() + " tasks in the list!\n"
        );
    }

    public int getSize() {
        return tasks.size();
    }

    private Task getTask(int index) {
        return tasks.get(index);
    }

    public String markDone(int taskNumber) {
        StringBuilder sb = new StringBuilder("____________________________________\n");
        Task task = getTask(taskNumber - 1);
        task.markDone();
        sb.append("Nice! I've marked this task as done:\n");
        sb.append(task);
        return sb.toString();
    }

    public String markUndone(int taskNumber) {
        StringBuilder sb = new StringBuilder("____________________________________\n");
        Task task = getTask(taskNumber - 1);
        task.markUndone();
        sb.append("Ok, I've marked this task as not done yet:\n");
        sb.append(task);
        return sb.toString();
    }

    @Override
    public String toString() {
        int count = 1;
        StringBuilder sb = new StringBuilder();
        sb.append("____________________________________").append("\n").append("Here are the tasks in your list:\n");

        for (Task task : tasks) {
            sb.append(count).append(". ").append(task.toString()).append("\n");
            count++;
        }

        sb.append("_____________________________________").append("\n");
        return sb.toString();
    }
}
