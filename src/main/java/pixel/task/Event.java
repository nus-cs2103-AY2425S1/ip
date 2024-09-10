package pixel.task;

import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.time.LocalDate;

import pixel.DateTimeParser;
import pixel.PixelException;

/**
 * Represents an event task. Inherits from the Task class.
 */
public class Event extends Task {
    private String type = "E";
    private LocalDate from;

    /**
     * Constructs a new Event object with the given description.
     *
     * @param description the description of the event
     * @throws PixelException if the description is empty or does not match the
     *                        required format
     */
    public Event(String description) throws PixelException {
        super(modifyDescription(description));
        this.extractFromDate(description);
    }

    /**
     * Constructs a new Event object with the given description and done status.
     *
     * @param description the description of the event
     * @param done        the done status of the event
     */
    public Event(String description, String done) throws PixelException {
        super(description, done);
        this.extractFromDate(description);
    }

    /**
     * Modifies the description of the event by extracting the start and end time
     * from the given description and formatting it.
     *
     * @param des the original description of the event
     * @return the modified description with start and end time
     * @throws PixelException if the description is empty or does not match the
     *                        required format
     */
    private static String modifyDescription(String des) throws PixelException {
        if (des.length() == 0) {
            throw new PixelException("OH NO!!! The description of Event cannot be empty!");
        }
        String regex = "(.*?) /from (.*?) /to (.*)";
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(des);
        if (matcher.find()) {
            String initalDes = matcher.group(1);
            DateTimeParser fromTime = new DateTimeParser(matcher.group(2));
            DateTimeParser toTime = new DateTimeParser(matcher.group(3));
            return String.format("%s (from: %s to: %s)", initalDes, fromTime, toTime);
        } else {
            throw new PixelException("Event should be of this format: {description} /from {date} /to {date}");
        }
    }

    private void extractFromDate(String des) throws PixelException {
        String[] parts = des.split(" ");
        String date = parts[parts.length - 1];
        DateTimeParser time = new DateTimeParser(date);
        this.from = time.getDateTime();
    }

    public LocalDate getFromDate() {
        return this.from;
    }

    /**
     * Returns the type of the event.
     *
     * @return the type of the event
     */
    @Override
    public String getType() {
        return this.type;
    }
}
