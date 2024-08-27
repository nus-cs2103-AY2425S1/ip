package Tasks;

import Default.Ui;
import Exceptions.NedException;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    public LocalDate deadlineTiming;

    private Deadline(String taskDescription, String deadlineTiming, boolean isDone) throws NedException {
        super(taskDescription, isDone);
        try {
            this.deadlineTiming = LocalDate.parse(deadlineTiming);
        } catch (DateTimeParseException e) {
            throw new NedException("M'lord, the time formatting in /by does not follow ISO 8601 (yyyy-mm-dd). Here " +
                    "are " +
                    "examples of" +
                    " " +
                    "valid " +
                    "timings:\n" + Ui.INDENTATIONS + "2015-08-04\n" + Ui.INDENTATIONS + "2015-08-04T10:11:30");
        }
        this.taskType = "D";
    }

    private String showTiming() {
            return String.format("%s %d %d", deadlineTiming.getMonth(), deadlineTiming.getDayOfMonth(),
                    deadlineTiming.getYear());
    }
    @Override
    public String toString() {
        return super.toString() + " " + String.format("(by: %s)", showTiming());
    }

    public static Deadline createDeadline(String taskDescription, String deadlineTiming, boolean taskStatus) throws NedException {
        if (taskDescription.isBlank()) {
            throw new NedException("M'lord, this saved deadline task has no task description!");
        } else if (deadlineTiming.isBlank()) {
            throw new NedException("M'lord, this saved deadline task has no due date!");
        };
        return new Deadline(taskDescription, deadlineTiming, taskStatus);
    }

    @Override
    public String toTextForm() {
        int status = this.isDone ? 1 : 0;
        return String.format("deadline|%d|%s|%s", status, this.taskDescription, this.deadlineTiming);
    }
}