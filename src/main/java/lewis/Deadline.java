package lewis;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task{
    private final LocalDateTime deadline;
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
     * Factory method for instantiating a deadline. This method should be used when reading a
     * deadline from file
     * @param description A string description of the task
     * @param status The status of the task
     * @param deadline The deadline as a LocalDateTime object
     * @return A deadline with those specifications
     */
     static Deadline of(String description,String status, LocalDateTime deadline) {
        Task.Status formattedStatus = Task.Status.valueOf(status);
        return new Deadline(description, formattedStatus, deadline);
    }

    /**
     * Factory method for instantiating a deadline. This method should be used to create a new
     * deadline, and is by default not done.
     * @param description A string description of the task
     * @param date A string representing the deadline in the format YYYY-MM-DD
     * @param time A string representing the time of the deadline in the format HH:MM
     * @return A deadline with those specifications
     */
    static Deadline of(String description, String date, String time) {
        LocalDate formattedDate = LocalDate.parse(date);
        LocalTime formattedTime = LocalTime.parse(time);
        LocalDateTime deadline = LocalDateTime.of(formattedDate,formattedTime);
        return new Deadline(description, Status.NOT_DONE, deadline);
    }


    @Override
    public String toString() {
        String str = "[D]" +
                super.toString() +
                " (Deadline: " +
                deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm")) +
                ")";
        return str;
    }

    /**
     * Returns a csv representation of this deadline.
     * @return A string in the form "Deadline,(task description),(task status),(deadline)"
     */
    @Override
    protected String toCsv() {
        String csv = "Deadline," +
                super.toCsv() +
                "," +
                this.deadline;
        return csv;
    }
}
