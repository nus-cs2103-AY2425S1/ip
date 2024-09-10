package task;

import java.time.LocalDateTime;

public class Deadline extends Task {
    private LocalDateTime endTime;

    public Deadline(String taskName, LocalDateTime endTime) {
        super(taskName);
        this.endTime = endTime;
    }

    /**
     * Converts the LocalDateTime object attribute to a clean looking string.
     * 
     * @return the date and time formatted like "10:30pm on AUGUST 29, 2024".
     */
    private String dateTimeToCleanString() {
        int hour = this.endTime.getHour() > 12 ? this.endTime.getHour() - 12 : this.endTime.getHour() == 0 ? 12 : this.endTime.getHour();
        int minute = this.endTime.getMinute();
        String meridian = this.endTime.getHour() >= 12 ? "pm" : "am";
        String time = minute != 0 ? hour + ":" + minute + meridian : hour + meridian;
        return time + " on " + this.endTime.getMonth().toString() + " " + this.endTime.getDayOfMonth() + ", " + this.endTime.getYear();
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.dateTimeToCleanString() + ")";
    }

    @Override
    public String toEasyString() {
        return "[D]" + super.toString() + " (by: " + this.endTime + ")";
    }

    @Override
    public void update(String newDesc, LocalDateTime fromTime, LocalDateTime toTime) {
        if (newDesc != null) {
            this.setTaskName(newDesc);
        }
        if (toTime != null) {
            this.endTime = toTime;
        }
    }
}
