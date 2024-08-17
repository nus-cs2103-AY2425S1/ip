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

    public void markTaskAsDone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setStatus(true);
        } else {
            System.out.println("Invalid task number, must be more than 0.");
        }
    }

    public void markTaskAsUndone(int index) {
        if (index >= 0 && index < tasks.size()) {
            tasks.get(index).setStatus(false);
        } else {
            System.out.println("Invalid task number, must be more than 0.");
        }
    }

    public Task getTask(int index) {
        if (index >= 0 && index < tasks.size()) {
            return tasks.get(index);
        } else {
            return null;
        }
    }
}
