import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeFormatterBuilder;
import java.time.format.DateTimeParseException;

/**
 * The Task class is an abstract representation of a task
 */
public abstract class Task {
    protected String description;
    protected boolean isDone;
    protected DateTimeFormatter formatterWithTime = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy HHmm"))
            .toFormatter();
    protected DateTimeFormatter formatterWithoutTime = new DateTimeFormatterBuilder()
            .appendOptional(DateTimeFormatter.ofPattern("yyyy-MM-dd"))
            .appendOptional(DateTimeFormatter.ofPattern("dd/MM/yyyy"))
            .appendOptional(DateTimeFormatter.ofPattern("d/MM/yyyy"))
            .toFormatter();
    protected DateTimeFormatter formatterDisplay = DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm");

    /**
     * Constructs a new Task with the specified description.
     * By default, the task is not marked as done.
     *
     * @param description The description of the task.
     */
    public Task(String description) throws StrandException{
        if (description.isEmpty()) {
            throw new StrandDescNotFoundException("Description");
        }
        this.description = description;
        this.isDone = false;
    }

    public LocalDateTime parseDate(String date) throws StrandException {
        try {
            return LocalDateTime.parse(date, formatterWithTime);
        } catch (DateTimeParseException e) {
            try {
                // If parsing as LocalDateTime fails, try to parse as LocalDate
                LocalDate localDate = LocalDate.parse(date, formatterWithoutTime);
                return localDate.atStartOfDay();
            } catch (DateTimeParseException e2) {
                throw new StandInvalidDateException(date);
            }
        }
    }

    public String parseOutputDate(LocalDateTime date) {
        return date.format(formatterDisplay);
    }

    private String getStatus() {
        return "[" + (isDone ? "X" : " ") + "] ";
    }

    abstract String getType();

    /**
     * Returns the string representation of the task
     *
     * @return A string representing the task
     */
    @Override
    public String toString() {
        return this.getStatus() + this.description; // mark done task with X
    }

    /**
     * Marks the task as done
     */
    public void markAsDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done
     */
    public void markAsNotDone() {
        this.isDone = false;
    }
}
