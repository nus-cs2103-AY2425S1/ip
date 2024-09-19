import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private LocalDateTime deadline;
    /**
     * Private constructor for a Task
     * @param description A string description of the task
     * @param status The status of the task(done or not done)
     * @param deadline A deadline formatted as a LocalDateTime object
     */
    private Deadline(String description, Task.Status status, LocalDateTime deadline){
        super(description, status);
        this.deadline = deadline;
    }

    /**
     * Factory method for instantiating a deadline
     * @param description A string description of the task
     * @param status The status of the task(done or not done)
     * @param date A string representing the deadline in the format YYYY-MM-DD
     * @param time A string representing the time of the deadline in the format HH:MM
     * @return A deadline with those specifications
     */
    public static Deadline of(String description, Task.Status status, String date, String time) {
        LocalDate formattedDate = LocalDate.parse(date);
        LocalTime formattedTime = LocalTime.parse(time);
        LocalDateTime deadline = LocalDateTime.of(formattedDate,formattedTime);
        return new Deadline(description, status, deadline);
    }

    /**
     * Factory method for instantiating an event while parsing from a csv file
     * This method should only be called when loading the event from Storage.
     * @param description A string representation of this event
     * @param status The event's completion status
     * @param deadline the deadline
     * @return a Deadline
     */
    protected static Deadline of(String description, String status, String deadline) {
        Task.Status newStatus = Task.Status.valueOf(status);
        LocalDateTime newDeadline = LocalDateTime.parse(deadline);
        return new Deadline(description, newStatus, newDeadline);
    }


    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("[D]");
        str.append(super.toString());
        str.append("(Deadline: ");
        str.append(deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")));
        str.append(")");
        return str.toString();
    }

    /**
     * Returns a csv representation of this deadline.
     * @return A string in the form "Deadline,(task description),(task status),(deadline)"
     */
    @Override
    protected String toCsv() {
        StringBuilder csv = new StringBuilder();
        csv.append("Deadline,");
        csv.append(super.toCsv());
        csv.append(",");
        csv.append(this.deadline);
        return csv.toString();
    }
}
