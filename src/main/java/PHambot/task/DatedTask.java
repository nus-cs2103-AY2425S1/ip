package PHambot.task;

import java.time.LocalDate;
import java.time.LocalTime;

public class DatedTask extends Task {

    private LocalDate date;
    private LocalTime time;

    public DatedTask(String task, String date, String time) {
        super(task);
        this.date = LocalDate.parse(date);
        this.time = LocalTime.parse(time);
    }

    public DatedTask(String task, String date) {
        super(task);
        this.date = LocalDate.parse(date);
    }

    public DatedTask() {
        super();
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
