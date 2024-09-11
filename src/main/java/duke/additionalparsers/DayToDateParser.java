package duke.additionalparsers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;

public class DayToDateParser {
    public static LocalDate getNextDateForDay(String s) {
        switch (s.toLowerCase()) {
        case "mon":
            s = "monday";
            break;
        case "tue":
            s = "tuesday";
            break;
        case "wed":
            s = "wednesday";
            break;
        case "thurs":
            s = "thursday";
            break;
        case "fri":
            s = "friday";
            break;
        case "sat":
            s = "saturday";
            break;
        case "sun":
            s = "sunday";
            break;
        }
        System.out.println(s);
        try {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(s.toUpperCase());
            LocalDate today = LocalDate.now();
            DayOfWeek todayDayOfWeek = today.getDayOfWeek();
            System.out.println(today);
            System.out.println(dayOfWeek);
            if (todayDayOfWeek.equals(dayOfWeek)) {
                return today;
            } else {
                return today.with(TemporalAdjusters.next(dayOfWeek));
            }
        } catch (Exception e){
            return null;
        }
    }

    public static void main(String[] args) {
        String day = "monff";
        LocalDate nextDate = getNextDateForDay(day);
        System.out.println("Next " + day + " is on: " + nextDate);
    }
}