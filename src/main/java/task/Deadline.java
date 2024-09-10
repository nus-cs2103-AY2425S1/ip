package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The {@code Deadline} class represents a task that needs to be completed by a specific date.
 * It extends the {@link Task} class by adding a deadline date to the task.
 * This class provides constructors for creating deadlines and methods for user input creation and string representation.
 */
public class Deadline extends Task {
    private LocalDate byTime;

    /**
     * Constructs a new {@code Deadline} object with the specified name, description, and deadline date.
     *
     * @param name The name of the deadline task.
     * @param description The description of the deadline task.
     * @param byTime The date by which the task needs to be completed.
     */
    public Deadline(String name, String description, LocalDate byTime) {
        super(name, description);
        this.byTime = byTime;
    }

    /**
     * Constructs a new {@code Deadline} object with the specified name, description, deadline date, and completion status.
     *
     * @param name The name of the deadline task.
     * @param description The description of the deadline task.
     * @param byTime The date by which the task needs to be completed.
     * @param isDone The completion status of the task. If {@code true}, the task is marked as done; otherwise, it is not done.
     */
    public Deadline(String name, String description, LocalDate byTime, boolean isDone) {
        super(name, description);
        this.byTime = byTime;
        this.isDone = isDone;
    }

    /**
     * Prompts the user to enter the details for a new {@code Deadline} and creates a new {@code Deadline} object.
     *
     * @param sc The {@link Scanner} object used to read user input.
     * @return A new {@code Deadline} object created based on user input.
     */
    public static Deadline createDeadline(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        LocalDate byTime = null;

        // Loop to prompt user for a valid deadline date
        while (true) {
            try {
                System.out.println("Enter deadline (yyyy-MM-dd): ");
                byTime = LocalDate.parse(sc.nextLine().trim());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
        return new Deadline(name, description, byTime);
    }

    public static Deadline createDeadline(String name, String description, LocalDate byTime) {
        return new Deadline(name, description, byTime);
    }

    public LocalDate getByTime() {
        return this.byTime;
    }

    /**
     * Returns a string representation of the {@code Deadline} task.
     * The format includes the type of task (Deadline) and the information about its completion status, name, description, and deadline date.
     *
     * @return A string representation of the {@code Deadline} task.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + "\n\tBy: " + byTime.getDayOfMonth() + "/" +
                byTime.getMonth().toString().substring(0, 3) + "/" + byTime.getYear();
    }
}
