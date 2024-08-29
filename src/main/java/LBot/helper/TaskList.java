package LBot.helper;

import LBot.task.Task;

import java.util.ArrayList;

/**
 * This class encapsulates all methods related to maintaining the list of tasks.
 */
public class TaskList {
    private ArrayList<Task> taskList;

    /**
     * Empty constructor for TaskList.
     */
    public TaskList() {
        this.taskList = new ArrayList<>();
    }

    /**
     * Public constructor for TaskList.
     *
     * @param taskList can be empty or loaded from file.
     */
    public TaskList(ArrayList<Task> taskList) {
        this.taskList = taskList;
    }

    /**
     * Adds a {@link Task} to the list.
     *
     * @param task to be added.
     */
    public void addTask(Task task) {
        this.taskList.add(task);
    }

    /**
     * Marks a {@link Task} to the list.
     *
     * @param id of task to be marked.
     */
    public void markTask(int id) {
        taskList.get(id - 1).mark();
    }

    /**
     * Deletes and removes {@link Task} from the list.
     *
     * @param id of task to be deleted.
     */
    public Task deleteTask(int id) {
        Task task = taskList.get(id - 1);
        taskList.remove(id - 1);
        return task;
    }

    /**
     * Get number of tasks in list.
     *
     * @return current size of {@link TaskList}.
     */
    public int getLength() {
        return taskList.size();
    }

    /**
     * Returns entire TaskList.
     *
     * @return TaskList
     */
    public ArrayList<Task> getTaskList() {
        return taskList;
    }

    /**
     * Returns specified task.
     *
     * @param id of the specified task.
     * @return {@link Task}.
     */
    public Task getTask(int id) {
        return taskList.get(id - 1);
    }

    @Override
    public String toString() {
        if (taskList.isEmpty()) {
            return "No tasks found.";
        }
        int count = 1;
        StringBuilder sb = new StringBuilder();
        for (Task task : taskList) {
            sb.append(count).append(". ");
            sb.append(task.toString());
            sb.append("\n");
            count++;
        }
        return sb.toString().trim();
    }
}
