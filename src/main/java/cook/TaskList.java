package cook;

import java.util.ArrayList;

import tasks.Task;

/**
 * TaskList class to store tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds task to tasks.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
    }

    /**
     * Marks or unmarks task.
     *
     * @param taskNo Task number in tasks.
     * @param toMark Mark or unmark task.
     * @return Success of marking or unmarking task.
     * @throws IndexOutOfBoundsException If task number does not indicate a task in tasks.
     */
    public boolean markTask(int taskNo, boolean toMark) throws IndexOutOfBoundsException {
        int indexNo = taskNo - 1;
        return this.tasks.get(indexNo).mark(toMark);
    }

    /**
     * Deletes task
     *
     * @param taskNo Task number in tasks.
     */
    public void deleteTask(int taskNo) {
        int indexNo = taskNo - 1;
        this.tasks.remove(indexNo);
    }

    /**
     * Finds task
     *
     * @param keyword String to find in task descriptions
     */
    public String findTask(String keyword) {
        StringBuilder taskStringBuilder = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            int taskNo = i + 1;
            Task task = this.tasks.get(i);
            if (task.toString().contains(keyword.toLowerCase())) {
                taskStringBuilder.append(taskNo).append(".").append(task.toString()).append("\n");
            }
        }
        return taskStringBuilder.toString().strip();
    }

    /**
     * @inheritDoc.
     */
    @Override
    public String toString() {
        StringBuilder taskStringBuilder = new StringBuilder();
        for (int i = 0; i < this.tasks.size(); i++) {
            int taskNo = i + 1;
            Task task = this.tasks.get(i);
            taskStringBuilder.append(taskNo).append(".").append(task.toString()).append("\n");
        }
        return taskStringBuilder.toString().strip();
    }
}
