package mendel.datetime;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class DateTimeManager {
    private String formattedDate;

    public DateTimeManager(String rawDate) {
        try {
            LocalDateTime date = LocalDateTime.parse(rawDate);
            this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        } catch (DateTimeParseException e) {
            boolean hasValidFormat = false;
            String[] possibleFormatsTimed = new String[] {"dd-MMM-yyyy HH:mm:ss", "dd/MMM/yyyy HH:mm:ss", "dd MMM yyyy HH:mm:ss",
                    "dd-MM-yyyy HH:mm:ss", "dd/MM/yyyy HH:mm:ss", "dd-MMM-yyyy HHmm", "dd/MMM/yyyy HHmm", "dd MMM yyyy HHmm",
                    "dd-MM-yyyy HHmm", "dd/MM/yyyy HHmm", "dd-MMM-yyyy HH mm", "dd/MMM/yyyy HH mm", "dd MMM yyyy HH mm",
                    "dd-MM-yyyy HH mm", "dd/MM/yyyy HH mm"};
            for (int i = 0; i < possibleFormatsTimed.length; i++) {
                if (this.isValidFormat(rawDate, DateTimeFormatter.ofPattern(possibleFormatsTimed[i]))) {
                    LocalDateTime date = LocalDateTime.parse(rawDate, DateTimeFormatter.ofPattern(possibleFormatsTimed[i]));
                    this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy, HH:mm"));
                    hasValidFormat = true;
                    break;
                }
            }
            String[] possibleFormatsUnTimed = new String[] {"dd-MMM-yyyy", "dd/MMM/yyyy", "dd MMM yyyy", "MMM dd yyyy",
                    "MMM, dd yyyy", "MM dd yyyy", "dd MM yyyy", "dd/MM/yyyy", "MM/dd/yyyy", "yyyy dd MM", "yyyy, dd MM", "dd-MM-yyyy",
                    "MM d yyyy", "d MM yyyy", "d/MM/yyyy", "MM/d/yyyy", "yyyy d MM", "yyyy, d MM", "d-MM-yyyy",
                    "M dd yyyy", "dd M yyyy", "dd/M/yyyy", "M/dd/yyyy", "yyyy dd M", "yyyy, dd M", "dd-M-yyyy",
                    "M d yyyy", "d M yyyy", "d/M/yyyy", "M/d/yyyy", "yyyy d M", "yyyy, d M", "d-M-yyyy"};
            for (int i = 0; i < possibleFormatsUnTimed.length; i++) {
                if (this.isValidFormat(rawDate, DateTimeFormatter.ofPattern(possibleFormatsUnTimed[i]))) {
                    LocalDate date = LocalDate.parse(rawDate, DateTimeFormatter.ofPattern(possibleFormatsUnTimed[i]));
                    this.formattedDate = date.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
                    hasValidFormat = true;
                    break;
                }
            }
            if (!hasValidFormat) {
                this.formattedDate = rawDate;
            }
        }

    }

    private boolean isValidFormat(String rawDate, DateTimeFormatter dateFormatter) {
        try {
            LocalDate date = LocalDate.parse(rawDate, dateFormatter);
            return true;
        } catch (DateTimeParseException e) {
            return false;
        }

    }

    @Override
    public String toString() {
        return this.formattedDate;
    }
}
