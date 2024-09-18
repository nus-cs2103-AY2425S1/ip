package skibidi.utils;

import skibidi.exceptions.SkibidiException;

/**
 * Represents a time in 24-hour format.
 */
public class MilitaryTime {
    protected String time;
    /**
     * Creates a time in 24-hour format.
     *
     * @param time The time in 24-hour format.
     * @throws SkibidiException If the time is not in the correct format.
     */
    public MilitaryTime(String time) throws SkibidiException {
        if (!isValidTime(time)) {
            throw new SkibidiException("Invalid time format. Please enter time in HHmm format.");
        }
        this.time = time;
    }
    /**
     * Checks if the time is in the correct format.
     *
     * @param time The time to be checked.
     * @return True if the time is in the correct format, false otherwise.
     */
    public boolean isValidTime(String time) {
        if (time.length() != 4) {
            return false;
        }
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        boolean isHourValid = hour >= 0 && hour < 24;
        boolean isMinuteValid = minute >= 0 && minute < 60;
        return isHourValid && isMinuteValid;
    }

    /**
     * Converts the time from 24-hour format to 12-hour format.
     *
     * @param time The time in 24-hour format.
     * @return The time in 12-hour format.
     */
    public String convertMilitaryTimeToStandardTime(String time) {
        int hour = Integer.parseInt(time.substring(0, 2));
        int minute = Integer.parseInt(time.substring(2));
        String period = "AM";
        if (hour >= 12) {
            period = "PM";
            if (hour > 12) {
                hour -= 12;
            }
        }
        return String.format("%d:%02d %s", hour, minute, period);
    }
    @Override
    public String toString() {
        return convertMilitaryTimeToStandardTime(time);
    }
}
