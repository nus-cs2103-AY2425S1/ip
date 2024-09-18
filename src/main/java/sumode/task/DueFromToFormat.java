package sumode.task;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

import sumode.util.Parser;

/**
 * A class made to store the 3 supported formats for due(in deadline) or from/to(in event)
 */
public class DueFromToFormat {
    private DueFromToType type;
    private LocalDate localDate;
    private LocalDateTime localDateTime;
    private String str;

    /**
     * Constructor for Event
     *
     * @param dueFromTo may be in the form of localDate/localDateTime/others
     */
    public DueFromToFormat(String dueFromTo) {
        // We test if it is of LocalDate Type
        try {
            localDate = Parser.parseLocalDate(dueFromTo);
            this.type = DueFromToType.LOCAL_DATE;
            return; //terminate early once confirmed type
        } catch (ParseException e) {
            // fall through
        }

        // we test if it is of LocalDateTimeType
        try {
            localDateTime = Parser.parseLocalDateTime(dueFromTo);
            this.type = DueFromToType.LOCAL_DATE_TIME;
            return;
        } catch (ParseException e) {
            //fall through
        }

        // if it is none, we do normal String method
        this.str = dueFromTo;
        this.type = DueFromToType.STRING;
    }

    @Override
    public String toString() {
        return switch (this.type) {
        case LOCAL_DATE -> this.localDate.format(DateTimeFormatter.ofPattern("MMM d yyyy"));
        case LOCAL_DATE_TIME -> this.localDateTime.format(DateTimeFormatter.ofPattern("MMM dd, yyyy HH:mm:ss"));
        case STRING -> this.str;
        };
    }

    /**
     * Compares 2 DueFromTo
     *
     * @return
     */
    public static boolean isValidRange(DueFromToFormat start, DueFromToFormat end) {
        if (start.type == DueFromToType.STRING || end.type == DueFromToType.STRING) {
            return true;
        } else if (start.type == DueFromToType.LOCAL_DATE) {
            if (end.type == DueFromToType.LOCAL_DATE) {
                return !start.localDate.isAfter(end.localDate);
            } else {
                return !start.localDate.atStartOfDay().isAfter(end.localDateTime);
            }
        } else { // start.type is Local_Date_Time
            if (end.type == DueFromToType.LOCAL_DATE_TIME) {
                return !start.localDateTime.isAfter(end.localDateTime);
            } else {
                return !start.localDateTime.isAfter(end.localDate.atTime(LocalTime.MAX));
            }
        }
    }
}
