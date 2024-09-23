package ava.task.tasks;

import java.time.format.DateTimeFormatter;

/**
 * Parses date and time strings.
 */
public class DateParser {
    public static final DateTimeFormatter PARSER = DateTimeFormatter.ofPattern("EEE, dd MMM yyyy HH:mm:ss");
}
