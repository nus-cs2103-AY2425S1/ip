package Tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Event extends Task {

    private LocalDateTime startLocalDateTime;
    private LocalDateTime endLocalDateTime;

    public Event(String taskName, String start, String end) {
        super(taskName);

        String[] startStrings = start.split("/");
        int startDayOfMonth = Integer.parseInt(startStrings[0]);
        int startMonth = Integer.parseInt(startStrings[1]);
        String[] startYearTime = startStrings[2].split(" ");
        int startYear = Integer.parseInt(startYearTime[0]);
        String startTime = startYearTime[1];
        String startHour = startTime.substring(0, 2);
        String startMinute = startTime.substring(2);
        LocalDate startLocalDate = LocalDate.of(
                startYear, startMonth, startDayOfMonth);
        LocalTime startLocalTime = LocalTime.of(
                Integer.parseInt(startHour), Integer.parseInt(startMinute));
        LocalDateTime startLocalDateTime = LocalDateTime.of(
                startLocalDate, startLocalTime);
        this.startLocalDateTime = startLocalDateTime;

        String[] endStrings = end.split("/");
        int endDayOfMonth = Integer.parseInt(endStrings[0]);
        int endMonth = Integer.parseInt(endStrings[1]);
        String[] endYearTime = endStrings[2].split(" ");
        int endYear = Integer.parseInt(endYearTime[0]);
        String endTime = endYearTime[1];
        String endHour =endTime.substring(0, 2);
        String endMinute = endTime.substring(2).split("\\)")[0];
        LocalDate endLocalDate = LocalDate.of(
                endYear, endMonth, endDayOfMonth);
        LocalTime endLocalTime = LocalTime.of(
                Integer.parseInt(endHour), Integer.parseInt(endMinute));
        LocalDateTime endLocalDateTime = LocalDateTime.of(
                endLocalDate, endLocalTime);
        this.endLocalDateTime = endLocalDateTime;

    }

    public static String extractName(String input) {
        String[] strings = input.split("\\s+", 2);
        String detail = strings[1];
        return detail.split(" /from ")[0];
    }

    public static String extractDate(String input, boolean isStart) {
        String[] strings = input.split("\\s+", 2);
        String detail = strings[1];
        String period = detail.split(" /from ")[1];
        if (isStart) {
            return period.split(" /to ")[0];
        } else {
            return period.split(" /to ")[1];
        }
    }



    @Override
    public String displayTask() {
        String cross ="";
        if (super.getDone()) {
            cross = "[X]";
        } else {
            cross = "[ ]";
        }
        String startMonth = this.startLocalDateTime.getMonth().getDisplayName(
                TextStyle.valueOf("SHORT"), new Locale("English"));
        String startDayOfMonth = String.valueOf(
                this.startLocalDateTime.getDayOfMonth());
        String startYear = String.valueOf(
                this.startLocalDateTime.getYear());
        String startTime = this.startLocalDateTime.toLocalTime().toString();

        String endMonth = this.endLocalDateTime.getMonth().getDisplayName(
                TextStyle.valueOf("SHORT"), new Locale("English"));
        String endDayOfMonth = String.valueOf(
                this.endLocalDateTime.getDayOfMonth());
        String endYear = String.valueOf(
                this.endLocalDateTime.getYear());
        String endTime = this.endLocalDateTime.toLocalTime().toString();

        return "[E]" + cross + " " + super.getInput()
                + " (from: " + startMonth + " " + startDayOfMonth + " " + startYear + " " + startTime
                + " to: " + endMonth + " " + endDayOfMonth + " " + endYear + " " + endTime + ")\n";
    }
}
