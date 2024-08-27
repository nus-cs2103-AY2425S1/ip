package mel.utils;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Parser {
    private static final String[] FORMAT_DATE_TIME = {
            "d-M-[uu][uuuu] HHmm",
            "d-M-[uu][uuuu] HH:mm",
            "d-M-[uu][uuuu] h.mma",
            "d-M-[uu][uuuu] hmma",
            "d-M-[uu][uuuu] ha",
            "d/M/[uu][uuuu] HHmm",
            "d/M/[uu][uuuu] HH:mm",
            "d/M/[uu][uuuu] h.mma",
            "d/M/[uu][uuuu] hmma",
            "d/M/[uu][uuuu] ha",
            "u-M-d HHmm",
            "u-M-d HH:mm",
            "u-M-d h.mma",
            "u-M-d hmma",
            "u-M-d ha",
            "u/M/d HHmm",
            "u/M/d HH:mm",
            "u/M/d h.mma",
            "u/M/d hmma",
            "u/M/d ha",
    };
    private static final String[] FORMAT_DATE = {
            "d-M-[uu][uuuu]",
            "d/M/[uu][uuuu]",
            "u/M/d",
            "u-M-d",
    };

    public LocalDateTime parseDateTime(String str) {
        for (String s : FORMAT_DATE) {
            try {
                return LocalDate.parse(str, DateTimeFormatter.ofPattern(s))
                        .atStartOfDay();
            } catch (DateTimeParseException e) {
                //Fallthrough
            }
        }
        for (String s : FORMAT_DATE_TIME) {
            try {
                return LocalDateTime.parse(str, DateTimeFormatter.ofPattern(s));
            } catch (DateTimeParseException e) {
                //Fallthrough
            }
        }
        throw new DateTimeParseException("", str, 0);
    }
}
