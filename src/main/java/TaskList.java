/**
 * The TaskList class represents list of tasks input by user.
 * As per requirements it can store up to 100 tasks.
 */
public class TaskList {
    private Task[] tasks;
    private int taskCount;

    /**
     * Constructs a new TaskList with an Array of 100 tasks.
     * Initializes the task count to zero.
     */
    public TaskList() {
        this.tasks = new Task[100];
        this.taskCount = 0;
    }

    /**
     * Adds a task to the TaskList.
     * @param task The task to be added.
     */
    public void addTask(String task) {
        //Create new task object from user input
        Task newTask = new Task(task);

        this.tasks[this.taskCount] = newTask;
        this.taskCount++;
        
        System.out.println("____________________________________________________________");
        System.out.println("added: " + task);
        System.out.println("____________________________________________________________");
    }

    /**
     * Returns a string representation of the task list.
     */
    @Override
    public String toString() {
        String result = "";
        for (int i = 0; i < this.taskCount; i++) {
            if (tasks[i] == null) {
                break;
            }
            result = result + i + "." + tasks[i] + "\n";
        }
        return result;
    }
}