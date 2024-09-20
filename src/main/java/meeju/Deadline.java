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
    private static final String DATE_PATTERN = "dd/MM/yyyy";
    private static final String TIME_PATTERN = "HHmm";
    private final String taskType;
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
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);

        LocalDate date;
        LocalTime time;
        try {
            date = LocalDate.parse(dateRaw, dateFormatter);
            time = LocalTime.parse(timeRaw, timeFormatter);
        } catch (DateTimeParseException e) {
            throw new MeejuException(exceptionMessage);
        }

        this.deadline = LocalDateTime.of(date, time);
        this.taskType = "D";
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
     * Returns if the deadline is after current date and time.
     * This is used to for processing task in other methods
     *
     * @return if the deadline is after current date and time
     */
    public boolean isFutureDeadline() {
        LocalDateTime currentDateTime = LocalDateTime.now();
        return this.deadline.isAfter(currentDateTime);
    }



    /**
     * Serializes the task details into a string format suitable for storage.
     * The delimiter used is "!-".
     *
     * @return A string representing the serialized details of the task.
     */
    @Override
    public String serializeDetails() {
        String fileDelimiter = "!-";
        String taskCharacter = "D";
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern(DATE_PATTERN);
        String formattedDate = this.deadline.toLocalDate().format(dateFormatter);
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern(TIME_PATTERN);
        String formattedTime = this.deadline.toLocalTime().format(timeFormatter);

        return taskCharacter + fileDelimiter + this.getIsDone() + fileDelimiter
                + this.getTaskDescription() + fileDelimiter
                + formattedDate + " " + formattedTime + "\n";
    }

    public String getTaskIdentifier() {
        return this.taskType;
    }

    @Override
    public String toString() {
        return TASK_ICON + super.toString() + " (by: " + this.getDeadline() + ")";
    }
}
