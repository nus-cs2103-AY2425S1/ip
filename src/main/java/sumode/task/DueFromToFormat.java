package sumode.task;

import java.text.ParseException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import sumode.util.Parser;

public class DueFromToFormat {
    DueFromToType type;
    LocalDate localDate;
    LocalDateTime localDateTime;
    String str;

    public DueFromToFormat(String dueFromTo) {
        // We test if it is of LocalDate Type
        try {
            localDate = Parser.parseLocalDate(dueFromTo);
            this.type = DueFromToType.LOCAL_DATE;
            return; //terminate early once confirmed type
        } catch (ParseException e){
            // fall through
        }

        // we test if it is of LocalDateTimeType
        try {
            localDateTime = Parser.parseLocalDateTime(dueFromTo);
            this.type = DueFromToType.LOCAL_DATE_TIME;
            return;
        } catch (ParseException e){
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
}
