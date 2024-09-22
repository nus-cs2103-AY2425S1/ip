package babygronk;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Stores dates as LocalDate and time as LocalTime.
 */
public class CustomDate {
    private LocalDate localDate;
    private LocalTime localTime;

    /**
     * CustomDate constructor method.
     *
     * @param date Date string of format dd/mm/yyyy hhmm or dd-mm-yyyy hhmm.
     */
    public CustomDate(String date) {
        try {
            parseDate(date);
        } catch (DateTimeParseException e) {
            System.out.println(e.getMessage());
            localDate = LocalDate.now();
            localTime = LocalTime.now();
        }
    }

    private void parseDate(String date) throws DateTimeParseException {
        if (date == null || date.isEmpty()) {
            throw new DateTimeParseException("Date cannot be empty", "", 0);
        }
        String[] args = date.split(" ");
        DateTimeFormatter formatter;
        if (args.length == 2) {
            if (args[0].contains("/")) {
                formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HHmm");
            } else if (args[0].contains("-")) {
                formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HHmm");
            } else {
                throw new DateTimeParseException("Invalid date format", date, 0);
            }
            LocalDateTime dateTime = LocalDateTime.parse(date, formatter);
            localDate = dateTime.toLocalDate();
            localTime = dateTime.toLocalTime();
        } else {
            throw new DateTimeParseException("Invalid date format", date, 0);
        }
    }

    /**
     * Formats the date and time to be stored into the data file so that it can be read easily next time.
     *
     * @return Date string with format dd/MM/yyyy HHmm.
     */
    public String unparseDate() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HHmm");
        return (localDate.format(DateTimeFormatter.ofPattern("dd/MM/yyyy")) + " " + localTime.format(formatter));
    }

    @Override
    public String toString() {
        assert localDate != null;
        int day = localDate.getDayOfMonth();
        StringBuilder date = new StringBuilder();
        if (day / 10 != 1) {
            switch (day % 10) {
            case 1:
                date.append(day).append("st");
                break;
            case 2:
                date.append(day).append("nd");
                break;
            case 3:
                date.append(day).append("rd");
                break;
            default:
                date.append(day).append("th");
            }
        } else {
            date.append(day).append("th");
        }
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("LLLL yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm a");
        return (date.append(" of ").append(localDate.format(formatter))
                .append(" at ").append(localTime.format(timeFormatter))).toString();
    }

}
