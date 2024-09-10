package task;

import java.time.LocalDateTime;

public class Event extends Task {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Event(String taskName, LocalDateTime start, LocalDateTime end) {
        super(taskName);
        this.startDate = start;
        this.endDate = end;
    }

    /**
     * Converts the LocalDateTime object to a cleaner looking string.
     * Similar to Deadline::dateTimeToCleanString.
     * 
     * @param dt the LocalDateTime object to be converted.
     * @return the date and time formatted like "10:30pm on AUGUST 29, 2024".
     */
    private String dateTimeToCleanString(LocalDateTime dt) {
        int hour = dt.getHour() > 12 ? dt.getHour() - 12 : dt.getHour() == 0 ? 12 : dt.getHour();
        int minute = dt.getMinute();
        String meridian = dt.getHour() >= 12 ? "pm" : "am";
        String time = minute != 0 ? hour + ":" + minute + meridian : hour + meridian;
        return time + " on " + dt.getMonth().toString() + " " + dt.getDayOfMonth() + ", " + dt.getYear();
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + dateTimeToCleanString(this.startDate)
                + ", to: " + dateTimeToCleanString(this.endDate) + ")";
    }

    @Override
    public String toEasyString() {
        return "[E]" + super.toString() + " (from: " + this.startDate + ", to: " + this.endDate + ")";
    }

    @Override
    public void update(String newDesc, LocalDateTime fromTime, LocalDateTime toTime) {
        if (newDesc != null) {
            this.setTaskName(newDesc);
        }
        if (fromTime != null) {
            this.startDate = fromTime;
        }
        if (toTime != null) {
            this.endDate = toTime;
        }
    }
}
