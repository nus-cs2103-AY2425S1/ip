package sigmabot.task;

import sigmabot.ui.UiComponent;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

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

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public static Event createEvent(UiComponent ui) {
        ui.printDialogue("Enter name: ");
        String name = ui.readInput().trim();
        ui.printDialogue("Enter description: ");
        String description = ui.readInput().trim();
        LocalDate startTime = null;
        LocalDate endTime = null;
        String location;
        while (true) {
            try {
                ui.printDialogue("Enter start time (yyyy-MM-dd): ");
                startTime = LocalDate.parse(ui.readInput().trim());
                break; // Exit loop if parsing succeeds
            } catch (DateTimeParseException e) {
                ui.printDialogue("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
        while (true) {
            try {
                ui.printDialogue("Enter end time (yyyy-MM-dd): ");
                endTime = LocalDate.parse(ui.readInput().trim());
                if (endTime.isBefore(startTime)) {
                    ui.printDialogue("End time cannot be before start time. Please enter a valid end time.");
                } else {
                    break;
                }
            } catch (DateTimeParseException e) {
                ui.printDialogue("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }
        ui.printDialogue("Enter location: ");
        location = ui.readInput().trim();
        return new Event(name, description, startTime, endTime, location);
    }

    @Override
    public String toString() {
        return "[E] " + super.toString() +
                "\n\tStart Time: " + startTime +
                "\n\tEnd Time: " + endTime +
                "\n\tLocation: " + location;
    }
}
