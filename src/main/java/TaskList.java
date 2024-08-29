import java.io.IOException;
import java.util.ArrayList;

/**
 * Represents a list of tasks.
 * This class manages a collection of tasks and provides methods to add and list them.
 */
public class TaskList {
    private ArrayList<Task> tasks;

    /**
     * Constructs an empty task list.
     */
    public TaskList(Storage storage){
        this.tasks = new ArrayList<Task>();
    }
    public TaskList() {
        this.tasks = new ArrayList<Task>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task){
        tasks.add(task);
    }


    /**
     * Deletes a task from the task list by index and writes the updated list to a file.
     *
     * @param index The index of the task to be deleted.
     * @return
     * @throws IOException If an I/O error occurs while writing to the file.
     */
    public Task delete(int index){
        Task task = tasks.get(index);
        tasks.remove(index);
        return task;
    }

    /**
     * Returns the number of tasks in the task list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task as done.
     */
    public Task markAsDone(int index) {
        Task task = tasks.get(index);
        task.done();
        return task;
    }

    public ArrayList<Task> getTasks() {
        return this.tasks;
    }


    /**
     * Marks the task as not done.
     */
    public Task markAsNotDone(int index) {
        Task task = tasks.get(index);
        task.notDone();
        return task;
    }

    /**
     * Returns a string representation of the task list.
     * Each task is represented with an index and a description.
     *
     * @return A string listing all tasks with their indices.
     */
    @Override
    public String toString() {
        String listOfTasks = "Here are the tasks in your list:\n";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;

    }
}


