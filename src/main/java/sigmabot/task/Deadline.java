package sigmabot.task;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class Deadline extends Task {
    private LocalDate byTime;

    public Deadline(String name, String description, LocalDate byTime) {
        super(name, description);
        this.byTime = byTime;
    }

    public static Deadline createDeadline(Scanner sc) {
        System.out.println("Enter name: ");
        String name = sc.nextLine().trim();
        System.out.println("Enter description: ");
        String description = sc.nextLine().trim();
        LocalDate byTime = null;
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

    @Override
    public String toString() {
        return "[D] " + super.toString() + "\n\tBy: " + byTime.getDayOfMonth() + "/" + byTime.getMonth().toString().substring(0, 3) + "/" + byTime.getYear();
    }
}
