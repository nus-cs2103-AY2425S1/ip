package rob;

import java.util.ArrayList;
import java.util.List;

public class TaskList {
    private List<Task> taskList;

    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.taskList = tasks;
    }

    public int len() {
        return taskList.size();
    }

    public Task getTask(int index) {
        if (index >= 0 && index < taskList.size()) {
            return taskList.get(index);
        } else {
            System.out.println("Invalid index: " + index);
            return null;
        }
    }

    public List<Task> getTasks() {
        return taskList;
    }

    public void removeTask(int i) {
        taskList.remove(i);
    }

    /**
     * Searches for tasks containing the specified keyword.
     * Returns a list of tasks that contain the keyword.
     *
     * @param keyword The keyword to search for in task descriptions.
     * @return A list of tasks that contain the specified keyword in their descriptions.
     * @throws DukeException If an error occurs while processing the tasks.
     */
    public List<Task> searchTasks(String keyword) throws DukeException {
        List<Task> matchingTasks = new ArrayList<>();
        for (Task task : taskList) {
            String line = task.toSaveString();
            String[] parts = line.split(" \\| ", 5);
            String desc = parts[2].trim();

            if (desc.contains(keyword)) {
                matchingTasks.add(task);
            }
        }
        return matchingTasks;
    }
}
