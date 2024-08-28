package oyster.tasks;

import oyster.exceptions.TaskFieldException;

import java.util.Scanner;

public class EventTask extends Task {
    public static final String FILE_SYMBOL = "E";

    private String from;
    private String to;

    public EventTask(String description, String from, String to) {
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

        if (name == "") {
            throw new TaskFieldException("Description");
        }
        if (from == "") {
            throw new TaskFieldException("From");
        }
        if (to == "") {
            throw new TaskFieldException("To");
        }

        return new EventTask(name.trim(), from, to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + String.format(" (from: %sto: %s)", from, to);
    }

    @Override
    public String[] compose() {
        return new String[] {
                FILE_SYMBOL,
                isMarked() ? "1" : "0",
                getDescription(),
                from,
                to
        };
    }
}
