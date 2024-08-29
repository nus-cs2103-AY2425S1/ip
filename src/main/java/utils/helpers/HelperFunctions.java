package utils.helpers;

import utils.exceptions.IllegalValueException;

import java.time.LocalDateTime;

public class HelperFunctions {
    public static LocalDateTime stringToDateTime(String str) throws IllegalValueException {
        return stringToDateTime(str, false);
    }

    public static LocalDateTime stringToDateTime(String str, boolean dateOnly) throws IllegalValueException {
        try {
            String[] dateTime = str.split(" ");

            String[] date = dateTime[0].split("-");
            int year = Integer.parseInt(date[2]);
            int month = Integer.parseInt(date[1]);
            int day = Integer.parseInt(date[0]);

            if (!dateOnly) {
                String time = dateTime[1];
                int hour = Integer.parseInt(time.substring(0, 2));
                int minute = Integer.parseInt(time.substring(2, 4));

                return LocalDateTime.of(year, month, day, hour, minute);
            }

            return LocalDateTime.of(year, month, day, 0, 0);
        } catch (Exception e) {
            throw new IllegalValueException("Date and time provided is not of correct format (dd-mm-yyyy hhmm): " + str);
        }
    }
}
