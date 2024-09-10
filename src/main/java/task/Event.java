package task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

/**
 * The {@code Event} class represents an event with a name, description, start time, end time, and location.
 * It extends the {@link Task} class to include specific details pertinent to events.
 * This class provides constructors for creating events and methods for user input creation and string representation.
 */
public class Event extends Task {
    private String location;
    private LocalDate startTime;
    private LocalDate endTime;

    /**
     * Constructs a new {@code Event} object with the specified name, description, start time, end time, and location.
     *
     * @param name The name of the event.
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param location The location of the event.
     */
    public Event(String name, String description, LocalDate startTime, LocalDate endTime, String location) {
        super(name, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    /**
     * Constructs a new {@code Event} object with the specified name, description, start time, end time, location, and completion status.
     *
     * @param name The name of the event.
     * @param description The description of the event.
     * @param startTime The start time of the event.
     * @param endTime The end time of the event.
     * @param location The location of the event.
     * @param isDone The completion status of the event. If {@code true}, the event is marked as done; otherwise, it is not done.
     */
    public Event(String name, String description, LocalDate startTime, LocalDate endTime, String location, boolean isDone) {
        super(name, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
        this.isDone = isDone;
    }
    /**
     * Prompts the user to enter the details for a new {@code Event} and creates a new {@code Event} object.
     *
     * @param sc The {@link Scanner} object used to read user input.
     * @return A new {@code Event} object created based on user input.
     */
    public static Event createEvent(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        LocalDate startTime = null;
        LocalDate endTime = null;
        String location;
        // Loop to prompt user for a valid start time
        while (true) {
            try {
                System.out.println("Enter start time (yyyy-MM-dd): ");
                startTime = LocalDate.parse(sc.nextLine().trim());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
        // Loop to prompt user for a valid end time
        while (true) {
            try {
                System.out.println("Enter end time (yyyy-MM-dd): ");
                endTime = LocalDate.parse(sc.nextLine().trim());
                if (endTime.isBefore(startTime)) {
                    System.out.println("End time cannot be before start time. Please enter a valid end time.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }

        System.out.println("Enter location: ");
        location = sc.nextLine().trim();
        return new Event(name, description, startTime, endTime, location);
    }

    public String getLocation() {
        return location;
    }

    public LocalDate getStartTime() {
        return startTime;
    }

    /**
     * Returns the end time of the event.
     *
     * @return The end time of the event.
     */
    public LocalDate getEndTime() {
        return endTime;
    }

    /**
     * Returns a string representation of the {@code Event} task.
     * The format includes the type of task (Event) and the information about its completion status, name, description, start time, end time, and location.
     *
     * @return A string representation of the {@code Event} task.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() +
                "\n\tStart Time: " + startTime +
                "\n\tEnd Time: " + endTime +
                "\n\tLocation: " + location;
    }
}
