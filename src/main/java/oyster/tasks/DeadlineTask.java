package oyster.tasks;

import java.time.LocalDateTime;
import java.util.Scanner;

import oyster.exceptions.TaskFieldException;
import oyster.utils.DateTimeFormatter;

/**
 * DeadlineTask contains a deadline.
 */
public class DeadlineTask extends Task {
    public static final String FILE_SYMBOL = "D";

    private final LocalDateTime deadline;

    /**
     * Creates a DeadlineTask given its values.
     *
     * @param description The description of the Task.
     * @param deadline The deadline of the Task.
     */
    public DeadlineTask(String description, LocalDateTime deadline) {
        super(description);

        assert deadline != null;

        this.deadline = deadline;
    }

    /**
     * Creates a DeadlineTask given an input String to parse.
     *
     * @param input The string to parse into a DeadlineTask.
     * @return DeadlineTask object.
     */
    public static DeadlineTask createFromInput(String input) {
        assert input != null;

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

        if (name.isEmpty()) {
            throw new TaskFieldException("Description");
        }
        if (deadline.isEmpty()) {
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
