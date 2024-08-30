package sigmabot.task;

import sigmabot.ui.UiComponent;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private LocalDate byTime;

    public Deadline(String name, String description, LocalDate byTime) {
        super(name, description);
        this.byTime = byTime;
    }

    public LocalDate getByTime() {
        return byTime;
    }

    public void setByTime(LocalDate byTime) {
        this.byTime = byTime;
    }

    public static Deadline createDeadline(UiComponent ui) {
        ui.printDialogue("Enter name: ");
        String name = ui.readInput().trim();

        ui.printDialogue("Enter description: ");
        String description = ui.readInput().trim();

        LocalDate byTime = null;

        // Prompt for byTime until valid input is received
        while (true) {
            try {
                ui.printDialogue("Enter deadline (yyyy-MM-dd): ");
                byTime = LocalDate.parse(ui.readInput().trim());
                break; // Exit loop if parsing succeeds
            } catch (DateTimeParseException e) {
                ui.printDialogue("Invalid date format. Please enter in the format yyyy-MM-dd.");
            }
        }

        return new Deadline(name, description, byTime);
    }

    @Override
    public String toString() {
        return "[D] " + super.toString() +
                "\n\tBy: " + byTime.getDayOfMonth() + "/" + byTime.getMonth().toString().substring(0, 3) + "/" + byTime.getYear();
    }
}
