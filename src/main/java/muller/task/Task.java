package muller.task;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class Task {
    public static final DateTimeFormatter INPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd");
    public static final DateTimeFormatter OUTPUT_DATE_FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy");

    private String name;
    private boolean isDone;
    private String type = "[ ]";  // [T], [D], [E]
    private LocalDate date; // For Deadline or Event start date
    private LocalDate endDate;   // For Event end date

    public Task(String name) {
        this.name = name;
        this.isDone = false;
    }

    public void setType(String type) {
        this.type = "[" + type + "]";
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDateRange(LocalDate startDate, LocalDate endDate) {
        this.date = startDate;
        this.endDate = endDate;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void markAsDone(boolean isDone) {
        this.isDone = isDone;
    }

    public void markAsNotDone() {
        this.isDone = false;
    }

    public boolean isOnDate(LocalDate date) {
        if (type.equals("[T]")) {
            return false; // Todo tasks don't have dates
        } else if (type.equals("[D]")) {
            return this.date != null && this.date.equals(date);
        } else if (type.equals("[E]")) {
            return this.date != null && this.endDate != null &&
                    (date.equals(this.date) || date.equals(this.endDate) ||
                            (date.isAfter(this.date) && date.isBefore(this.endDate)));
        }
        return false;
    }

    public String DoneIcon() {
        return (isDone ? "[X]" : "[ ]");
    }

    @Override
    public String toString() {
        String dateStr = "";
        if (type.equals("[D]") && date != null) {
            dateStr = " (by: " + date.format(OUTPUT_DATE_FORMATTER) + ")";
        } else if (type.equals("[E]") && date != null && endDate != null) {
            dateStr = " (from: " + date.format(OUTPUT_DATE_FORMATTER) + " to: " + endDate.format(OUTPUT_DATE_FORMATTER) + ")";
        }
        return this.type + DoneIcon() + " " + name + dateStr;
    }

    public String toFileString() {
        StringBuilder sb = new StringBuilder();
        sb.append(type.charAt(1)).append(" | ").append(isDone ? "1" : "0").append(" | ").append(name);
        if (type.equals("[D]")) {
            sb.append(" | ").append(date.format(INPUT_DATE_FORMATTER));
        } else if (type.equals("[E]")) {
            sb.append(" | ").append(date.format(INPUT_DATE_FORMATTER))
                    .append(" | ").append(endDate.format(INPUT_DATE_FORMATTER));
        }
        return sb.toString();
    }
}

