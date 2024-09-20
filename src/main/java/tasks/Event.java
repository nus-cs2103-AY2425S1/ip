package tasks;

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
        this.startLocalDateTime = convertStringToLdt(start);
        this.endLocalDateTime = convertStringToLdt(end.split("\\)")[0]);
    }

    /**
     * Converts date in String format DD/MM/YYYY HHmm to LocalDateTime
     *
     * @param string Date in format DD/MM/YYYY
     * @return LocalDateTime Corresponding LocalDateTime
     */
    public static LocalDateTime convertStringToLdt(String string) {
        String[] strings = string.split("/");
        int dayOfMonth = Integer.parseInt(strings[0]);
        int month = Integer.parseInt(strings[1]);
        String[] yearTime = strings[2].split(" ");
        int year = Integer.parseInt(yearTime[0]);
        String time = yearTime[1];
        String hour = time.substring(0, 2);
        String minute = time.substring(2);
        LocalDate localDate = LocalDate.of(
                year, month, dayOfMonth);
        LocalTime localTime = LocalTime.of(
                Integer.parseInt(hour), Integer.parseInt(minute));
        return LocalDateTime.of(localDate, localTime);
    }

    /**
     * Converts LocalDateTime to date in String format DD Month YYYY HH:mm
     *
     * @param ldt LocalDateTime
     * @return date in format DD Month YYYY HH:mm
     */
    public static String convertLdtToString(LocalDateTime ldt) {
        String month = ldt.getMonth().getDisplayName(
                TextStyle.valueOf("SHORT"), new Locale("English"));
        String dayOfMonth = String.valueOf(
                ldt.getDayOfMonth());
        String year = String.valueOf(
                ldt.getYear());
        String time = ldt.toLocalTime().toString();
        return month + " " + dayOfMonth + " " + year + " " + time;
    }

    /**
     * Extract name of task from user's input of format {taskName} /from {date} /to {date}
     *
     * @param input User's input without command, of format {taskName} /from {date} /to {date}
     * @return Name of task
     */
    public static String extractName(String input) {
        String[] strings = input.split(" /from ", 2);
        return strings[0];
    }

    /**
     * Extract date from user's input of format {taskName} /from {date} /to {date}
     *
     * @param detail User's input without command, of format {taskName} /from {date} /to {date}
     * @param isStart True if extracting start date, false if extracting end date
     * @return Date of format DD/MM/YYYY HHmm
     */
    public static String extractDate(String detail, boolean isStart) {
        String period = detail.split(" /from ")[1];
        if (isStart) {
            return period.split(" /to ")[0];
        } else {
            return period.split(" /to ")[1];
        }
    }

    @Override
    public String displayTask() {
        String cross = displayDone();
        String exclamationMark = displayPriority();
        String startLdtString = convertLdtToString(this.startLocalDateTime);
        String endLdtString = convertLdtToString(this.endLocalDateTime);
        return exclamationMark + "[E]" + cross + " " + super.getInput()
                + " (from: " + startLdtString + " to: " + endLdtString + ")\n";
    }
}
