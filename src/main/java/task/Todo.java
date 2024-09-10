package task;

import java.util.Scanner;

/**
 * The {@code Todo} class represents a simple task with a name and description.
 * It can be marked as done or not done. This class extends the {@link Task} class.
 * The {@code Todo} class provides constructors to create a new task and a static method to create a task through user input.
 */
public class Todo extends Task {

    /**
     * Constructs a new {@code Todo} object with the specified name and description.
     * By default, the task is not marked as done.
     *
     * @param name The name of the task.
     * @param description The description of the task.
     */
    public Todo(String name, String description) {
        super(name, description);
    }

    /**
     * Constructs a new {@code Todo} object with the specified name, description, and completion status.
     *
     * @param name The name of the task.
     * @param description The description of the task.
     * @param isDone The completion status of the task. If {@code true}, the task is marked as done; otherwise, it is not done.
     */
    public Todo(String name, String description, boolean isDone) {
        super(name, description);
        this.isDone = isDone;
    }

    /**
     * Prompts the user to enter the details for a new {@code Todo} task and creates a new {@code Todo} object.
     *
     * @param sc The {@link Scanner} object used to read user input.
     * @return A new {@code Todo} object created based on user input.
     */
    public static Todo createTodo(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        return new Todo(name, description);
    }

    /**
     * Prompts the user to enter the details for a new {@code Todo} task and creates a new {@code Todo} object.
     *
     * @param sc The {@link Scanner} object used to read user input.
     * @return A new {@code Todo} object created based on user input.
     */
    public static Todo createTodo(String name, String description) {
        return new Todo(name, description);
    }

    /**
     * Returns a string representation of the {@code Todo} task.
     * The format includes the type of task (Todo) and the information about its completion status, name, and description.
     *
     * @return A string representation of the {@code Todo} task.
     */
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
