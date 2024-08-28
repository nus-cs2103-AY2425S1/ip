package oyster.tasks;

import oyster.exceptions.TaskFieldException;

import java.util.Scanner;

public class DeadlineTask extends Task {
    public static final String FILE_SYMBOL = "D";

    private String deadline;

    public DeadlineTask(String description, String deadline) {
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

        return new DeadlineTask(name.trim(), deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", deadline);
    }

    @Override
    public String[] compose() {
        return new String[] {
                FILE_SYMBOL,
                isMarked() ? "1" : "0",
                getDescription(),
                deadline
        };
    }
}
