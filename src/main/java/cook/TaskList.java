package cook;

import java.util.ArrayList;

import tasks.Task;

/**
 * TaskList class to store tasks.
 */
public class TaskList {
    protected ArrayList<Task> tasks;

    /**
     * Constructor for TaskList class.
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
     * Marks task.
     *
     * @param taskNo Task number in tasks.
     * @return Success of marking or unmarking task.
     * @throws IndexOutOfBoundsException If task number does not indicate a task in tasks.
     */
    public boolean markTask(int taskNo) throws IndexOutOfBoundsException {
        int indexNo = taskNo - 1;
        return this.tasks.get(indexNo).mark();
    }

    /**
     * Unmarks task.
     *
     * @param taskNo Task number in tasks.
     * @return Success of marking or unmarking task.
     * @throws IndexOutOfBoundsException If task number does not indicate a task in tasks.
     */
    public boolean unmarkTask(int taskNo) throws IndexOutOfBoundsException {
        int indexNo = taskNo - 1;
        return this.tasks.get(indexNo).unmark();
    }

    /**
     * Deletes task.
     *
     * @param taskNo Task number in tasks.
     */
    public void deleteTask(int taskNo) {
        int indexNo = taskNo - 1;
        this.tasks.remove(indexNo);
    }

    /**
     * Finds task.
     *
     * @param keyword String to find in task descriptions.
     */
    public TaskList findTask(String keyword) {
        TaskList foundTasks = new TaskList();
        for (Task task : this.tasks) {
            if (task.toString().contains(keyword.toLowerCase())) {
                foundTasks.addTask(task);
            }
        }
        return foundTasks;
    }

    /**
     * Checks if tasks is empty.
     */
    public boolean isEmpty() {
        return this.tasks.isEmpty();
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
