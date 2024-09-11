package topaz.main;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import topaz.task.Task;

/**
 * Represents a list of tasks.
 * This class manages the collection of tasks, allowing for adding, removing, and updating tasks.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs a {@link TaskList} with the specified initial list of tasks.
     *
     * @param tasks An {@link ArrayList} of {@link Task} objects to initialize the task list with.
     */
    public TaskList(ArrayList<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Constructs an empty {@link TaskList}.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The {@link Task} to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
    }


    /**
     * Removes a task from the task list by its index.
     *
     * @param index The index of the task to remove (1-based index).
     * @return The {@link Task} that was removed.
     */
    public Task removeTask(int index) {
        return tasks.remove(index);
    }



    /**
     * Lists all tasks in the task list, displaying their status.
     * Outputs each task with its index and status to the console.
     */
    public String listTasks() {
        String listString = "";
        for (int i = 0; i < this.tasks.size(); i++) {
            listString += (i + 1) + ". " + this.tasks.get(i).getStatus() + "\n";
        }
        return listString;
    }

    /**
     * Marks a task as done by its index.
     *
     * @param index The index of the task to mark as done (1-based index).
     * @return The {@link Task} that was marked as done.
     */
    public Task markAsDone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setDone();
        return task;
    }

    /**
     * Marks a task as undone by its index.
     *
     * @param index The index of the task to mark as undone (1-based index).
     * @return The {@link Task} that was marked as undone.
     */
    public Task markAsUndone(int index) {
        Task task = this.tasks.get(index - 1);
        task.setUndo();
        return task;
    }

    public int getSize() {
        return tasks.size();
    }

    /**
     * Writes the current tasks in the task list to a file using the specified {@link FileWriter}.
     *
     * @param fw The {@link FileWriter} used to write tasks to the file.
     * @throws IOException If an error occurs while writing to the file.
     */
    public void write(FileWriter fw) throws IOException {
        for (Task t: this.tasks) {
            fw.write(t.toFileRecord() + "\n");
        }
    }

    /**
     * Find a task by searching for a keyword.
     * @param filter
     * @return task conatins the filter keyword in description
     */
    public TaskList find(String filter) {
        ArrayList<Task> targetTasks = new ArrayList<>();
        for (Task t: tasks) {
            if (t.toString().contains(filter)) {
                targetTasks.add(t);
            }
        }
        return new TaskList(targetTasks);
    }
    @Override
    public boolean equals(Object object) {
        if (!(object instanceof TaskList)) {
            return false;
        }
        TaskList other = (TaskList) object;
        for (int i = 0; i < other.getSize(); i++) {
            if (!this.tasks.get(i).equals(other.tasks.get(i))) {
                return false;
            }
        }
        return true;
    }

    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < tasks.size(); i++) {
            result += (i + 1) + ". " + tasks.get(i).getStatus() + "\n";
        }
        return result;
    }
}
