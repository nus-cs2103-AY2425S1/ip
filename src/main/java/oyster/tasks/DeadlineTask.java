package oyster.tasks;

import oyster.exceptions.TaskFieldException;
import oyster.utils.DateTimeFormatter;

import java.util.Scanner;
import java.time.LocalDateTime;

public class DeadlineTask extends Task {
    public static final String FILE_SYMBOL = "D";

    private final LocalDateTime deadline;

    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static DeadlineTask fromInput(String input) {
        String name = "";
        String deadline = "";
        boolean isDeadlineNext = false;

        Scanner scanner = new Scanner(input);
        while (scanner.hasNext()) {
            String next = scanner.next();
            if (scanner.hasNext()) {
                next += " ";
            }

            if (isDeadlineNext) {
                deadline = next.trim();
                break;
            }

            if (next.trim().equals("/by")) {
                isDeadlineNext = true;
            } else {
                name += next;
            }
        }

        scanner.close();

        if (name == "") {
            throw new TaskFieldException("Description");
        }
        if (deadline == "") {
            throw new TaskFieldException("Deadline");
        }

        return new DeadlineTask(name.trim(), DateTimeFormatter.readInput(deadline));
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format(" (by: %s)",
                DateTimeFormatter.format(deadline));
    }

    @Override
    public String[] compose() {
        return new String[] {
                FILE_SYMBOL,
                isMarked() ? "1" : "0",
                getDescription(),
                deadline.toString()
        };
    }
}
