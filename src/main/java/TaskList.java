import java.util.ArrayList;

public class TaskList {
    private final ArrayList<Task> list = new ArrayList<>();

    public void addTask(String title, TaskType type) {
        Task task = Task.of(title, type);
        list.add(task);
        System.out.printf("Got it. I've added this task:\n %s\nNow you have %s tasks in the list.%n", task, list.size());
    }

    public void markTaskAsDone(int index) {
        list.get(index - 1).markAsDone();
    }

    public void markTaskAsUndone(int index) {
        list.get(index - 1).markAsUndone();
    }

    @Override
    public String toString() {
        StringBuilder string = new StringBuilder();

        for (int i = 0; i < list.size(); i++) {
            if (i > 0) {
                string.append("\n");
            }
            string.append(Integer.toString(i + 1)).append(". ").append(list.get(i).toString());
        }

        return string.toString();
    }
}