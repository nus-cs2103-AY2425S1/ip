package elster.tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class EventTask extends Task {
    LocalDateTime start;
    LocalDateTime end;

    public EventTask(String description, LocalDateTime start, LocalDateTime end) {
        super(description);
        this.start = start;
        this.end = end;
    }

    public static EventTask of(String input) {
        String[] splitInput = input.split("\\s+");
        LocalDateTime start;
        LocalDateTime end;

        if (input.strip().equals("event")) {
            printLine();
            System.out.println("    Elster \"kindly\" requests you to have details for your task");
            printLine();
            return null;

        } else if (!input.contains("/from")) {
            printLine();
            System.out.println("    Elster \"kindly\" requests you to have a start time with /from");
            printLine();
            return null;

        } else if (!input.contains("/to")) {
            printLine();
            System.out.println("    Elster \"kindly\" requests you to have a end time with /to");
            printLine();
            return null;

        }

        try {
            String startStr = input.substring(input.indexOf("/from") + 6 , input.indexOf("/to")).strip();
            String endStr = input.substring(input.indexOf("/to") + 4).strip();

            if (startStr.length() == 10 && endStr.length() == 10) {
                start = LocalDate.parse(startStr).atTime(23, 59);
                end = LocalDate.parse(endStr).atTime(23, 59);

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                start = LocalDateTime.parse(startStr, formatter);
                end = LocalDateTime.parse(endStr, formatter);
            }

        } catch (Exception e) {
            printLine();
            System.out.println("    Elster \"gently\" requests a yyyy-mm-dd or yyyy-mm-dd HH:mm format");
            printLine();
            return null;
        }

        return new EventTask(
                input.substring(6 , input.indexOf("/from")).strip(),
                start,
                end
        );
    }
    @Override
    public String toString() {
        if (this.status) {
            return "[E][X] " + this.description + " (from: " + start  + " to: " + end + ")";
        } else {
            return "[E][ ] " + this.description + " (from: " + start  + " to: " + end + ")";
        }
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (this.status) {
            return "E | 1 | " + this.description + " | " + this.start.format(formatter)
                    + " | " + this.end.format(formatter);
        } else {
            return "E | 0 | " + this.description + " | " + this.start.format(formatter)
                    + " | " + this.end.format(formatter) ;
        }
    }
}
