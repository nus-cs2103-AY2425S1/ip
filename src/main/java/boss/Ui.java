package boss;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * Represents the class that deals with the
 * interactions with the user.
 */
public class Ui {

    /**
     * Prints an error message when
     * an exception occurs!
     */
    public void showLoadingError() {
        System.out.println("There was an error!");
    }

    public void showLoadingError(Exception e) {
        System.out.println(e.getMessage());
    }

    public boolean hasDate(String s) {
        if (s.matches("\\d{4}-\\d{2}-\\d{2}") || s.matches("\\d{4}-\\d{2}-\\d{2} ")) {
            return true;
        }
        return false;
    }

    public boolean hasDateTime(String s) {
        if (s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2}") || s.matches("\\d{4}-\\d{2}-\\d{2} \\d{2}:\\d{2} ")) {
            return true;
        }
        return false;
    }

    public String formatDateTime(String s, boolean time) {
        if (!time) {
            LocalDate date = LocalDate.parse(s);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy "));
        } else {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            LocalDateTime date = LocalDateTime.parse(s, formatter);
            return date.format(DateTimeFormatter.ofPattern("MMM d yyyy HH:mm "));
        }

    }


}
