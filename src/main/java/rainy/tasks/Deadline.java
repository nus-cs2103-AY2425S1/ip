package rainy.tasks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a deadline task. This class is a sub-class of the <code>Task</code> class.
 */
public class Deadline extends Task {
    private static final String DATE_FORMAT = "MMM d yyyy";
    private static final String TIME_FORMAT = "HH:mm";
    private static final int START_TASK_TIME = 0;
    private static final int END_TASK_TIME = 10;
    private static final int HOUR_START = 11;
    private static final int HOUR_END = 13;
    private static final int MINUTE_START = 13;
    private static final int MINUTE_END = 15;
    private static final int COMPARE_DATE_START = 3;
    private static final int COMPARE_DATE_END = 13;
    private static final int SECOND_DATE_START = 14;
    private static final int SECOND_DATE_END = 19;
    private String endDate;

    /**
     * Constructs a new <code>Deadline</code> object.
     * @param name     Represents the name of the task.
     * @param endDate  Represents the endDate of the task.
     */
    public Deadline(String name, String endDate) {
        super(name);
        this.endDate = endDate;
    }

    public String getEndDate() {
        return this.endDate;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setDate(String date) {
        this.endDate = date;
    }

    /**
     * Represents the task in a readable format. If the deadline is read from an existing file, it is directly read into
     * name of the deadline. Else, this method does additional formatting to represent the date in a standard format.
     * {@code
     * Deadline deadline = new Deadline("return book", "02-12/2024", "1800");
     * System.out.println(deadline);
     *
     * // Expected output:
     * // [D] return book  (by Dec 2 2019 18:00)
     * }
     * @return  Returns a string representing the <code>Deadline</code> object.
     */
    @Override
    public String toString() {
        try {
            this.compareDate = LocalDate.parse(this.endDate.substring(START_TASK_TIME, END_TASK_TIME));
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            String newTime = LocalTime.parse(this.endDate.substring(HOUR_START, HOUR_END) + ":"
                    + this.endDate.substring(MINUTE_START, MINUTE_END))
                        .format(DateTimeFormatter.ofPattern(TIME_FORMAT));
            return "[D] " + super.getName() + "(by " + newDate + " " + newTime + ")";
        } catch (Exception e) {
            String secondDate = "";
            try {
                this.compareDate = LocalDate.parse(this.endDate.substring(COMPARE_DATE_START, COMPARE_DATE_END),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                secondDate = this.endDate.substring(SECOND_DATE_START, SECOND_DATE_END);
            } catch (Exception d) {
                this.compareDate = LocalDate.parse(this.endDate.substring(COMPARE_DATE_START, COMPARE_DATE_END + 1),
                        DateTimeFormatter.ofPattern(DATE_FORMAT));
                secondDate = this.endDate.substring(SECOND_DATE_START + 1, SECOND_DATE_END + 1);
            }
            String newDate = this.compareDate.format(DateTimeFormatter.ofPattern(DATE_FORMAT));
            return "[D] " + super.getName() + "(" + "by " + newDate + " " + secondDate + ")";
        }
    }
}
