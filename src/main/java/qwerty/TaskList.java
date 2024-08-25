package qwerty;

import java.util.ArrayList;
import qwerty.task.Task;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        tasks = list;
    }

    public int getSize() {
        return tasks.size();
    }

    public Task getTask(int index) throws IndexOutOfBoundsException {
        return tasks.get(index - 1);
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        if (task != null) {
            this.tasks.add(task);
        }
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) throws IndexOutOfBoundsException {
        tasks.remove(index - 1);
    }

    /**
     * Returns the list of tasks.
     */
    public String listTasks() {
        int taskNumber = 1;
        StringBuilder out = new StringBuilder();
        for (Task task: tasks) {
            out.append("\n").append(taskNumber).append(".").append(task);
            taskNumber++;
        }
        return out.toString();
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsDone(int index) throws IndexOutOfBoundsException {
        tasks.get(index - 1).markAsDone();
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsNotDone(int index) {
        tasks.get(index - 1).markAsNotDone();
    }

    /**
     * Returns a string generated from the tasks currently in the task list.
     *
     * @return String containing task details.
     */
    public String generateSaveString() {
        return tasks.stream()
                .map(Task::getAllDetails)
                .map(x -> String.join("|", x) + "\n")
                .reduce("", (s1, s2) -> s1 + s2);
    }
}
