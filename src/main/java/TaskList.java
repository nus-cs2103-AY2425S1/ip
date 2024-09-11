import java.util.List;

/**
 * Manages the list of tasks in the application.
 * @author Aaron
 */
public class TaskList {
    private static List<Task> tasks;

    /**
     * Constructs a TaskList with the specified list of tasks.
     *
     * @param tasks the initial list of tasks
     */
    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    /**
     * Adds a Todo task to the list.
     *
     * @param description the description of the Todo task
     */
    public void addTodo(String description) {
        Todo newTodo = new Todo(description, false);
        tasks.add(newTodo);
        Ui.addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                tasks.size() + " tasks in our list now.");
        Ui.addLine();
    }

    /**
     * Adds a Deadline task to the list.
     *
     * @param description the description of the Deadline task
     * @param dueBy the due date and time of the Deadline task
     */
    public void addDeadline(String description, String dueBy) {
        Deadline newDeadline = new Deadline(description, false, dueBy);
        tasks.add(newDeadline);
        Ui.addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                tasks.size() + " tasks in our list now.");
        Ui.addLine();
    }

    /**
     * Adds an Event task to the list.
     *
     * @param description the description of the Event task
     * @param start the start date and time of the Event task
     * @param end the end date and time of the Event task
     */
    public void addEvent(String description, String start, String end) {
        Event newEvent = new Event(description, false, start, end);
        tasks.add(newEvent);
        Ui.addLine();
        System.out.println("Alright, I've added this task:\n  " + tasks.get(tasks.size() - 1) + "\nWe have " +
                tasks.size() + " tasks in our list now.");
        Ui.addLine();
    }

    /**
     * Deletes a task from the list.
     *
     * @param index the index of the task that is to be deleted from the task list
     */
    public void deleteTask(int index) {
        // Informs the user that the task has been deleted
        Ui.addLine();
        System.out.println("Okay, I've removed this task:\n  " + tasks.get(index).toString() + "\nWe have " +
                (tasks.size() - 1) + " tasks in our list now.");
        Ui.addLine();
        // Deletes the task from the list
        tasks.remove(index);
    }

    /**
     * Lists all the tasks in the task list.
     */
    public void listTasks() {
        Ui.addLine();
        System.out.println("Here are the tasks in your list:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + "." + tasks.get(i));
        }
        Ui.addLine();
    }

    /**
     * Marks a task as done with an 'X'.
     *
     * @param index the index of the task to be marked as done
     */
    public void markTask(int index) {
        tasks.get(index).done();
        // Informs the user that the task has been marked as done
        Ui.addLine();
        System.out.println("Great! I've marked it as done:\n  " + tasks.get(index).toString());
        Ui.addLine();
    }

    /**
     * Unmarks a task by removing any 'X'.
     *
     * @param index the index of the task to be unmarked
     */
    public void unmarkTask(int index) {
        tasks.get(index).notDone();
        // Informs the user that the task has been marked as not done
        Ui.addLine();
        System.out.println("Alright, I've unchecked this task:\n  " + tasks.get(index).toString());
        Ui.addLine();
    }
}
