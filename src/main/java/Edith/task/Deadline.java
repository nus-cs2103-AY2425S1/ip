package task;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Deadline extends Task {
    private LocalDateTime dueDate;
    private String taskStringToSave;

    public Deadline(String task, String dueDateString) {
        super("[D] ", task);
        this.dueDate = parseDueDate(dueDateString);
        this.taskStringToSave = task + " /by " + dueDateString;
    }

    private LocalDateTime parseDueDate(String dueDateString) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("d/M/yyyy HHmm");
        return LocalDateTime.parse(dueDateString, formatter);
    }

    public LocalDate getDueDate() {
        return this.dueDate.toLocalDate();
    }

    @Override
    public String savedTaskString() {
        return taskStringToSave;
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("E, d MMM yyyy, ha", Locale.ENGLISH);
        String formattedDueDate = this.dueDate.format(formatter);
        String string = " (due: " + formattedDueDate + ")";
        return typeOfTaskString() + statusString() + taskString() + string;
    }
}
