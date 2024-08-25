import java.time.LocalDateTime;

public class Deadline extends Task {
    LocalDateTime endTime;

    public Deadline(String taskName, LocalDateTime endTime) {
        super(taskName);
        this.endTime = endTime;
    }

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
}
