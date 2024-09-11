package duke.additionalparsers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DayToDateParser {
    public static LocalDate getNextDateForDayOfWeek(String s) {
        switch (s.toLowerCase()) {
        case "mon":
            s = "monday";
        case "tue":
            s = "tuesday";
        case "wed":
            s = "wednesday";
        case "thu":
            s = "thursday";
        case "fri":
            s = "friday";
        case "sat":
            s = "saturday";
        case "sun":
            s = "sunday";
        default:
            break;
        }
        try {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(s.toUpperCase());
            LocalDate today = LocalDate.now();
            LocalDate nextDate = today.with(TemporalAdjusters.next(dayOfWeek));
            return nextDate;
        } catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        String day = "nigg";
        LocalDate nextDate = getNextDateForDayOfWeek(day);
        System.out.println("Next " + day + " is on: " + nextDate);
    }
}