import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        } else {
            System.out.println("Cannot add null task.");
        }
    }

    public String listTasks() {
        if (tasks.isEmpty()) {
            return "No Tasks added yet, pls add tasks!";
        }
        StringBuilder taskListString = new StringBuilder();
        int counter = 1;
        for (Task task : tasks) {
            taskListString.append(counter).append(". ").append(task.toString()).append("\n");
            counter++;
        }
        return taskListString.toString();
    }
}
