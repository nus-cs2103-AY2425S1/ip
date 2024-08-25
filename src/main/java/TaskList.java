import java.util.ArrayList;

public class TaskList {

    private final ArrayList<Task> tasks;

    public TaskList() {
        tasks = new ArrayList<>();
    }

    public TaskList(ArrayList<Task> list) {
        tasks = list;
    }

    /**
     * Adds a task to the task list.
     *
     * @param task The task to be added.
     */
    public void addTask(Task task) {
        this.tasks.add(task);
        System.out.println("\nGot it. I've added this task:\n" + task
                + "\nNow you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ")
                + "in the list.");
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index The index of the task to be deleted.
     */
    public void deleteTask(int index) {
        try {
            Task task = tasks.get(index - 1);
            tasks.remove(index - 1);
            System.out.println("\nNoted. I've removed this task:\n" + task
                    + "\nNow you have " + tasks.size() + (tasks.size() == 1 ? " task " : " tasks ")
                    + "in the list.");
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThat index is invalid.");
        }
    }

    /**
     * Deletes the task at the given index.
     *
     * @param index String describing the index of the task to be deleted.
     */
    public void deleteTask(String index) {
        try {
            deleteTask(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a number as the index.");
        }
    }

    /**
     * Prints the list of tasks.
     */
    public void listTasks() {
        System.out.println("\nHere are the tasks in your list:");
        int taskNumber = 1;
        for (Task task: tasks) {
            System.out.println(taskNumber + "." + task);
            taskNumber++;
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsDone(int index) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsDone();
            System.out.println("\nNice! I've marked this task as done:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThat index is invalid.");
        }
    }

    /**
     * Marks a task as done.
     *
     * @param index String representing the index of the task to be marked.
     */
    public void markTaskAsDone(String index) {
        try {
            markTaskAsDone(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a number as the index.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index The index of the task to be marked, starting from 1.
     */
    public void markTaskAsNotDone(int index) {
        try {
            Task task = tasks.get(index - 1);
            task.markAsNotDone();
            System.out.println("\nOK, I've marked this task as not done yet:\n" + task);
        } catch (IndexOutOfBoundsException e) {
            System.out.println("\nThat index is invalid.");
        }
    }

    /**
     * Marks a task as not done.
     *
     * @param index String representing the index of the task to be marked.
     */
    public void markTaskAsNotDone(String index) {
        try {
            markTaskAsNotDone(Integer.parseInt(index));
        } catch (NumberFormatException e) {
            System.out.println("\nPlease enter a number as the index.");
        }
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
