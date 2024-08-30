package sigmabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Event extends Task {
    private String location;
    private LocalDate startTime;
    private LocalDate endTime;

    public Event(String name, String description, LocalDate startTime, LocalDate endTime, String location) {
        super(name, description);
        this.startTime = startTime;
        this.endTime = endTime;
        this.location = location;
    }

    public static Event createEvent(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        LocalDate startTime = null;
        LocalDate endTime = null;
        String location;
        while (true) {
            try {
                System.out.println("Enter start time (yyyy-MM-dd): ");
                startTime = LocalDate.parse(sc.nextLine().trim());
                break;
            } catch (DateTimeParseException e) {
                System.out.println("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
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

    @Override
    public String toString() {
        return "[E] " + super.toString() + "\n\tStart Time: " + startTime + "\n\tEnd Time: " + endTime + "\n\tLocation: " + location;
    }
}
