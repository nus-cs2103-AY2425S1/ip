package Johnson.task;

import java.time.LocalDate;
import java.time.LocalTime;

/**
 * Represents a task with a date and optional time.
 */
public class DatedTask extends Task {

    private LocalDate date;
    private LocalTime time;

    /**
     * Constructs a DatedTask with the specified task name, date, time and optional tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param time the time of the task.
     * @param tags the tags of the task.
     */
    public DatedTask(String task, String date, String time, String... tags) {
        super(task, tags);
        this.date = LocalDate.parse(date);
        if (time != null) {
            this.time = LocalTime.parse(time);
        }
    }

    /**
     * Constructs a DatedTask with the specified task name, date and optional tags.
     *
     * @param task the name of the task.
     * @param date the date of the task.
     * @param tags the tags of the task.
     */
    public DatedTask(String task, String date, String... tags) {
        super(task, tags);
        this.date = LocalDate.parse(date);
    }

    public DatedTask() {
        super();
    }

    public DatedTask(String task, String date, String time) {
        super(task);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public LocalDate getDate() {
        return this.date;
    }

    public LocalTime getTime() {
        return this.time;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public void setDate(String date) {
        this.date = LocalDate.parse(date);
    }

    public void setTime(LocalTime time) {
        this.time = time;
    }

    public void setTime(String time) {
        this.time = LocalTime.parse(time);
    }
}
