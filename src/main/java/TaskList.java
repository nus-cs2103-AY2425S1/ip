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
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        tasks.add(task);
        System.out.println("____________________________________________________________");
        System.out.println("Got it. I've added this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks left in the list", tasks.size()));
        System.out.println("____________________________________________________________");
    }
    public void delete(int index) {
        Task task = tasks.get(index);
        tasks.remove(index);
        System.out.println("____________________________________________________________");
        System.out.println("Noted. I've removed this task:");
        System.out.println(task);
        System.out.println(String.format("Now you have %d tasks left in the list", tasks.size()));
        System.out.println("____________________________________________________________");

    }
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks the task as done.
     */
    public void markAsDone(int index) {
        Task task = tasks.get(index);
        task.done();
    }

    /**
     * Marks the task as not done.
     */
    public void markAsNotDone(int index) {
        Task task = tasks.get(index);
        task.notDone();
    }

    /**
     * Returns a string representation of the task list.
     * Each task is represented with an index and a description.
     *
     * @return A string listing all tasks with their indices.
     */
    @Override
    public String toString() {
        String listOfTasks = "Here are the tasks in your list: \n";
        for (int i = 0; i < tasks.size(); i++) {
            listOfTasks += (i + 1) + ". " + tasks.get(i) + "\n";
        }
        return listOfTasks;

    }
}


