package task;

import exceptions.AlreadyCompletedException;

import java.time.LocalDate;
import java.time.LocalTime;

public class Deadline extends Task {
    private LocalDate dueDate;

    public Deadline(String title, LocalDate dueDate) {
        super(title);
        this.dueDate = dueDate;
    }

    public static Deadline of(String title, String dueDateString) {
        String[] args = dueDateString.split("/at");
        LocalDate dueDate = LocalDate.parse(args[0].trim());
        if (args.length > 1) {
            return new TimeSpecificDeadline(title, dueDate, LocalTime.parse(args[1].trim()));
        }
        return new Deadline(title, dueDate);
    }

    public static Deadline of(String[] args) throws AlreadyCompletedException {
        Deadline deadline = Deadline.of(args[2], args[3]);
        if (Boolean.parseBoolean(args[1])) {
            deadline.complete();
        }
        return deadline;
    }

    @Override
    public String getTypeIcon() {
        return "D";
    }

    @Override
    public String toData() {
        return super.toData() + "|" + dueDate;
    }

    @Override
    public String toString() {
        return super.toString() + " by " + dueDate;
    }
}
