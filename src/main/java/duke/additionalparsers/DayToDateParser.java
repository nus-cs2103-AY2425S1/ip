package duke.additionalparsers;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.temporal.TemporalAdjusters;
/**
 * Utility class for parsing day abbreviations and finding the next date for that day of the week.
 */
public class DayToDateParser {
    /**
     * Returns the next date for the specified day of the week.
     *
     * <p>Given a day abbreviation (e.g., "mon" for Monday), this method finds the next occurrence of that
     * day starting from today. If today is the specified day, it returns today's date. If the day abbreviation
     * is not recognized, it returns {@code null}.</p>
     *
     * @param s the day abbreviation (e.g., "mon", "tue", etc.)
     * @return the next {@code LocalDate} for the specified day, or {@code null} if the day abbreviation is invalid
     */
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
        default:
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
        } catch (Exception e) {
            return null;
        }
    }

    public static void main(String[] args) {
        String day = "monff";
        LocalDate nextDate = getNextDateForDay(day);
        System.out.println("Next " + day + " is on: " + nextDate);
    }
}
