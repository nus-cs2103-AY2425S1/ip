import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> tasks = new ArrayList<>();

    public TaskList() {}

    public void addTask(Task task) throws JarException {
        if (task == null) {
            throw new JarException("Cannot add a null task.");
        }
        this.tasks.add(task);
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

    public void markTaskAsDone(int index) throws JarException {
        Task task = getTask(index);
        task.setStatus(true);
    }

    public void markTaskAsUndone(int index) throws JarException {
        Task task = getTask(index);
        task.setStatus(false);
    }

    public Task getTask(int index) throws JarException {
        if (index < 0 || index >= tasks.size()) {
            throw new JarException("Invalid task number!");
        }
        return tasks.get(index);
    }

    public int getTaskCount() {
        return tasks.size();
    }
}
