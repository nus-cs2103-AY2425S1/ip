import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DeadlineTask extends Task{
    LocalDateTime deadline;
    private DeadlineTask(String description, LocalDateTime deadline) {
        super(description);
        this.deadline = deadline;
    }

    public static DeadlineTask of(String input) {
        String[] splitInput = input.split("\\s+");
        LocalDateTime deadline;

        if (input.strip().equals("deadline")) {
            printLine();
            System.out.println("    Elster begs of you to have details for your task");
            printLine();
            return null;

        } else if (!input.contains("/by")) {
            printLine();
            System.out.println("    Elster begs of you to have a yknow, deadline with /by");
            printLine();
            return null;

        }

        try {
            String deadlineStr = input.substring(input.indexOf("/by") + 4).strip();

            if (deadlineStr.length() == 10) {
                deadline = LocalDate.parse(deadlineStr).atTime(23, 59);

            } else {
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
                deadline = LocalDateTime.parse(deadlineStr, formatter);
            }

        } catch (Exception e) {
            printLine();
            System.out.println("    for /by, Elster requires a yyyy-mm-dd or yyyy-mm-dd HH:mm format please and thanks");
            printLine();
            return null;
        }

        return new DeadlineTask(
                input.substring(8, input.indexOf("/by")).strip(),
                deadline
        );
    }

    @Override
    public String toString() {
        if (this.status) {
            return "[D][X] " + this.description + " (by: " + deadline + ")";
        } else {
            return "[D][ ] " + this.description + " (by: " + deadline + ")";
        }
    }

    @Override
    public String toFileString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        if (this.status) {
            return "D | 1 | " + this.description + " | " + this.deadline.format(formatter);
        } else {
            return "D | 0 | " + this.description + " | " + this.deadline.format(formatter);
        }
    }
}
