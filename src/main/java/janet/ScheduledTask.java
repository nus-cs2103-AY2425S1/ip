package janet;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents a task that has a schedule (date and time).
 */
public class ScheduledTask extends Task {
    private final LocalDateTime scheduledDateAndTime;

    /**
     * @param description The description of the task.
     * @param symbol The symbol of the task.
     * @param scheduledDateAndTime The date and time of the task, in LocalDateTime.
     */
    public ScheduledTask(String description, String symbol, LocalDateTime scheduledDateAndTime) {
        super(description, symbol);
        this.scheduledDateAndTime = scheduledDateAndTime;
    }


    /**
     * Converts and returns the input date and time into appropriate formats
     * date -> MM dd yyyy
     * time -> hh:mm a
     *
     * @param inputDate A string representing a date in the format, yyyy-MM-dd
     * @param inputTime A string representing a time in the format, hh:mm
     * @return A string representing the time and date (MM dd yyyy hh:mm a)
     */
    public static String DateAndTimeFormatter(String inputDate, String inputTime) {
        LocalDate localDate = LocalDate.parse(inputDate);
        String date = localDate.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        LocalTime localTime = LocalTime.parse(inputTime);
        String time = localTime.format(DateTimeFormatter.ofPattern("HH:mm a"));
        return date + " " + time;
    }

    @Override
    public LocalDateTime getScheduledDateAndTime() {
        return scheduledDateAndTime;
    }

    /**
     * @return The date portion of the scheduledDateAndTime.
     */
    @Override
    public LocalDate getScheduledDate() {
        return scheduledDateAndTime.toLocalDate();
    }

    /**
     * Finds and returns the index of the first keyword instance inside the String array.
     *
     * @param commandDetails a String[], where each element corresponds to a word of the user input.
     * @param keyword a String keyword that specifies what to be searched for in commandDetails.
     * @return the index of the keyword in commandDetails.
     */
    public static int getIndexOfKeyword(String[] commandDetails, String keyword) {
        int indexOfKeyword = 0;
        // first word in commandDetails belong to the command, so start from i = 1.
        for (int i = 1; i < commandDetails.length; i++) {
            if (commandDetails[i].equals(keyword)) {
                indexOfKeyword = i;
                break;
            }
        }
        return indexOfKeyword;
    }
}
