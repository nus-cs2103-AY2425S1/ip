package notgpt.parsers;

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
        try {
            DayOfWeek dayOfWeek = DayOfWeek.valueOf(convert(s).toUpperCase());
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

    private static String convert(String s) {
        switch (s.toLowerCase()) {
        case "mon" -> s = "monday";
        case "tues" -> s = "tuesday";
        case "tue" -> s = "tuesday";
        case "wed" -> s = "wednesday";
        case "thurs" -> s = "thursday";
        case "fri" -> s = "friday";
        case "sat" -> s = "saturday";
        case "sun" -> s = "sunday";
        default -> s = s;
        }
        return s;
    }

    public static void main(String[] args) {
        String day = "fri";
        LocalDate nextDate = getNextDateForDay(day);
        System.out.println("Next " + day + " is on : " + nextDate);
    }
}
