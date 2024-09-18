package tasks;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.TextStyle;
import java.util.Locale;

public class Deadline extends Task {

    private LocalDateTime localDateTime;

    public Deadline(String taskName, String deadline) {
        super(taskName);
        this.localDateTime = convertStringToLdt(deadline);
    }

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

    public static String extractName(String detail) {
        String[] strings = detail.split(" /by ", 2);
        return strings[0];
    }

    public static String extractDate(String detail) {
        String[] strings = detail.split(" /by ", 2);
        return strings[1];
    }

    @Override
    public String displayTask() {
        String cross = this.displayDone();
        String exclamationMark = this.displayPriority();
        String ldtString = convertLdtToString(this.localDateTime);
        return exclamationMark + "[D]" + cross + " " + super.getInput()
                + " (by: " + ldtString + ")\n";
    }
}
