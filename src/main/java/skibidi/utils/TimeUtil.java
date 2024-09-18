package skibidi.utils;

/**
 * Represents a utility class for converting time between standard and military time.
 */
public class TimeUtil {
    /**
     * Converts standard time to military time.
     *
     * @param standardTime Standard time to be converted.
     * @return Standard time.
     */
    public static String convertStandardToMilitary(String standardTime) {
        String[] parts = standardTime.split(" ");
        String time = parts[0];
        String period = parts[1];
        String[] timeParts = time.split(":");
        int hour = Integer.parseInt(timeParts[0]);
        int minute = Integer.parseInt(timeParts[1]);

        if (period.equalsIgnoreCase("PM") && hour != 12) {
            hour += 12;
        } else if (period.equalsIgnoreCase("AM") && hour == 12) {
            hour = 0;
        }
        return String.format("%02d%02d", hour, minute);
    }
}
