import java.util.Scanner;

public class DeadlineTask extends Task {
    private String deadline;

    public DeadlineTask(String name, String deadline) {
        super(name);
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

        return new DeadlineTask(name, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + String.format("(by: %s)", deadline);
    }
}
