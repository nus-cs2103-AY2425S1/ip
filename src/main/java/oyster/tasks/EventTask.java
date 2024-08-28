package oyster.tasks;

import oyster.exceptions.DateFormatException;
import oyster.exceptions.OysterException;
import oyster.exceptions.TaskFieldException;
import oyster.utils.DateTimeFormatter;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EventTask extends Task {
    public static final String FILE_SYMBOL = "E";

    private LocalDateTime from;
    private LocalDateTime to;

    public EventTask(String description, LocalDateTime from, LocalDateTime to) {
        super(description);
        this.from = from;
        this.to = to;
    }

    public static EventTask fromInput(String input) {
        String name = "";
        String from = "";
        String to = "";
        int isDateCounter = 0;

        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (scanner.hasNext()) {
                next += " ";
            }

            if (isDateCounter == 1) {
                isDateCounter++;
            } else if (isDateCounter == 3) {
                isDateCounter++;
            }

            if (next.trim().equals("/from") || next.trim().equals("/to")) {
                isDateCounter++;
            } else {
                if (isDateCounter == 2) {
                    from += next;
                } else if (isDateCounter == 4) {
                    to += next;
                } else {
                    name += next;
                }
            }
        }

        scanner.close();

        if (name.isEmpty()) {
            throw new TaskFieldException("Description");
        }
        if (from.isEmpty()) {
            throw new TaskFieldException("From");
        }
        if (to.isEmpty()) {
            throw new TaskFieldException("To");
        }

        LocalDateTime fromDate = DateTimeFormatter.readInput(from);
        LocalDateTime toDate = DateTimeFormatter.readInput(to);

        if (fromDate.isAfter(toDate)) {
            throw new DateFormatException();
        }

        return new EventTask(name.trim(), fromDate, toDate);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %s to: %s)",
                DateTimeFormatter.format(from), DateTimeFormatter.format(to));
    }

    @Override
    public String[] compose() {
        return new String[] {
                FILE_SYMBOL,
                isMarked() ? "1" : "0",
                getDescription(),
                from.toString(),
                to.toString()
        };
    }
}
