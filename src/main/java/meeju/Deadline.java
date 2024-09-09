package meeju;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Represents a task with a deadline.
 * This class extends the <code>Task</code> class having deadline as an additional property.
 */
public class Deadline extends Task {
    private static final String TASK_ICON = "[D]";

    private LocalDateTime deadline;

    /**
     * Constructor for a deadline task.
     * Parses the given deadline and converts it into a LocalDateTime object.
     *
     * @param taskDescription The description of the task.
     * @param taskDeadline The deadline for the task in the format "DD/MM/YYYY HHMM".
     * @throws MeejuException If the deadline format is incorrect or if parsing the date or time fails.
     */
    public Deadline(String taskDescription, String taskDeadline) throws MeejuException {
        super(taskDescription, false);

        assert taskDeadline != null : "Deadline task taskDeadline field is null";

        String[] dateAndTime = taskDeadline.split(" ");
        String exceptionMessage = "I'm having a bit of trouble understanding the task.\n"
                + "Could you please explain it using the correct format?\n"
                + "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM";
        if (dateAndTime.length != 2) {
            throw new MeejuException(exceptionMessage);
        }
        String dateRaw = dateAndTime[0];
        String timeRaw = dateAndTime[1];
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateRaw, dateFormatter);
            time = LocalTime.parse(timeRaw, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new MeejuException("Meow! Please recheck date and time you have entered \n"
                    + "The Correct format is -> deadline <desc> /by DD/MM/YYYY HHMM");
        }

        this.deadline = LocalDateTime.of(date, time);
    }

    /**
     * Returns the deadline of the task in a readable format.
     * To be used with toString method
     *
     * @return A string representing the deadline in the format "MMM d yyyy HHmm HRS".
     */
    public String getDeadline() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String formattedDate = this.deadline.toLocalDate().format(dateFormatter);
        LocalTime time = this.deadline.toLocalTime();
        return formattedDate + " " + time + "HRS";
    }

    /**
     * Serializes the task details into a string format suitable for storage.
     * The delimiter used is "!-".
     *
     * @return A string representing the serialized details of the task.
     */
    @Override
    public String serializeDetails() {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String formattedDate = this.deadline.toLocalDate().format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HHmm");
        String formattedTime = this.deadline.toLocalTime().format(timeFormatter);

        return "D !- " + this.getIsDone() + "!- "
                + this.getTaskDescription() + "!- "
                + formattedDate + " " + formattedTime + "\n";
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
