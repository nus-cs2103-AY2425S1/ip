import java.util.*;

/**
 * Represents a list of tasks. The TaskList class manages the creation,
 * manipulation, and display of tasks, including Todos, Deadlines, and Events.
 *
 * @author Jordan Chan
 */
public class TaskList {
    private final List<Task> tasks;
    /**
     * Constructs an empty TaskList.
     */
    public TaskList() {
        tasks = new ArrayList<>();
    }

    /**
     * Adds a task to the task list based on the input string.
     *
     * @param task The task description.
     * @throws DelphiException If the task is invalid or an error occurs.
     */
    public void addTask(String task) throws DelphiException {
        if (!task.equals("list")) {
            if (Parser.checkStringPrefix(task, 4, "todo")) {
                Task tsk;
                if (task.length() > 4) {
                    tsk = new Todo(task.substring(5));
                } else {
                    tsk = new Todo("");
                }
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (Parser.checkStringPrefix(task, 8, "deadline")) {
                Task tsk;
                if (task.length() > 8) {
                    tsk = new Deadline(task.substring(9));
                } else {
                    tsk = new Todo("");
                }
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else if (Parser.checkStringPrefix(task, 5, "event")) {
                Task tsk;
                if (task.length() > 5) {
                    tsk = new Event(task.substring(6));
                } else {
                    tsk = new Event("");
                }
                tasks.add(tsk);
                System.out.println("    Got it. I've added this task:");
                System.out.println("      " + tsk);
                System.out.println("Now you have " + tasks.size() + " tasks in the list.");
            } else {
                throw new InvalidInputException();
            }
        } else {
            printTasks();
        }
    }

    /**
     * Removes a task from the list based on the index.
     *
     * @param i The index of the task to be removed.
     * @return The removed task, or null if the index is invalid.
     */
    public Task removeTask(int i) throws InvalidListItemException {
        if (i <= tasks.size()) {
            Task t = tasks.get(i - 1);
            tasks.remove(i - 1);
            return t;
        } else {
            throw new InvalidListItemException(i);
        }
    }

    /**
     * Returns the number of tasks in the list.
     *
     * @return The number of tasks.
     */
    public int getSize() {
        return tasks.size();
    }

    /**
     * Marks a task as done based on the index.
     *
     * @param i The index of the task to be marked as done.
     */
    public void markTaskAsDone(int i) {
        if (i <= tasks.size()) {
            tasks.get(i - 1).complete();
        }
        //may want to add error handling for invalid indexes here
    }

    /**
     * Marks a task as undone based on the index.
     *
     * @param i The index of the task to be marked as undone.
     */
    public void markTaskAsUndone(int i) {
        if (i <= tasks.size()) {
            tasks.get(i - 1).uncomplete();
        }
        //may want to add error handling for invalid indexes here
    }

    /**
     * Returns the string representation of a task based on the index.
     *
     * @param i The index of the task.
     * @return The string representation of the task, or an empty string if the index is invalid.
     */
    public String getTask(int i) {
        if (i <= tasks.size()) {
            return tasks.get(i - 1).toString();
        } else {
            return "";
        }
    }

    /**
     * Prints all tasks in the task list.
     */
    public void printTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println("    " + (i + 1) + "." + tasks.get(i).toString());
        }
    }
}