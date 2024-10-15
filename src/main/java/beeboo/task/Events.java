package beeboo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import beeboo.components.TimeConverter;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;
import beeboo.exception.UpdateCommandException;

/**
 * The Events class represents an event task with a start date and an end date.
 * It extends the Tasks class and provides functionality for managing events,
 * including creation, formatting, and updating times.
 */
public class Events extends Tasks {

    /**
     * A formatter used for displaying date and time in the pattern "MMM dd yyyy 'at' hh:mm a".
     */
    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("dd MMM yyyy 'at' hh:mm a");

    /**
     * The start date and time of the event.
     */
    private LocalDateTime startDate;

    /**
     * The end date and time of the event.
     */
    private LocalDateTime endDate;

    /**
     * Constructs an Events instance with the specified description, start date, and end date.
     *
     * @param description the description of the event
     * @param startDate   the start date and time of the event
     * @param endDate     the end date and time of the event
     */
    public Events(String description, LocalDateTime startDate, LocalDateTime endDate) {
        super(description);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    /**
     * Returns the type icon for events.
     *
     * @return the type icon as a String, "[E]"
     */
    @Override
    public String typeIcon() {
        return "[E]";
    }

    /**
     * Returns a String representation of the Events instance, including the description,
     * start date, and end date formatted using the predefined formatter.
     *
     * @return a String representation of the event
     */
    @Override
    public String toString() {
        return typeIcon() + super.toString()
                + " (from: " + FORMATTER.format(startDate) + " to: " + FORMATTER.format(endDate) + ")";
    }

    /**
     * Creates an Events instance based on a given string input.
     *
     * @param text the string containing the event description, start date, and end date
     * @return a new Events object
     * @throws InvalidDateException   if the date format is invalid
     * @throws NoDescriptionException if the description is missing
     */
    public static Events createEvent(String text) throws InvalidDateException, NoDescriptionException {
        String[] parts = splitEventText(text);
        String description = extractDescription(parts[0]);
        LocalDateTime startDateTime = parseDateTime(parts[1], "from");
        LocalDateTime endDateTime = parseEndDateTime(parts[2], startDateTime);

        return new Events(description, startDateTime, endDateTime);
    }

    /**
     * Splits the event string into description, start date, and end date parts.
     *
     * @param text the input string to be split
     * @return an array containing the description, start date, and end date
     * @throws InvalidDateException if the string does not follow the correct event format
     */
    private static String[] splitEventText(String text) throws InvalidDateException {
        String[] parts = text.split("/");
        if (parts.length != 3) {
            throw new InvalidDateException("Invalid event format: " + text);
        }
        return parts;
    }

    /**
     * Extracts the description part of the event.
     *
     * @param descriptionPart the part of the string containing the description
     * @return the description of the event
     * @throws NoDescriptionException if the description is missing
     */
    private static String extractDescription(String descriptionPart) throws NoDescriptionException {
        String description = descriptionPart.trim();
        if (description.isEmpty()) {
            throw new NoDescriptionException("No description provided");
        }
        return description;
    }

    /**
     * Parses the date and time string to extract the LocalDateTime object.
     *
     * @param datePart       the part of the string containing the date and time
     * @param expectedPrefix the expected prefix in the string (e.g., "from")
     * @return the parsed LocalDateTime object
     * @throws InvalidDateException if the date format is invalid or does not start with the expected prefix
     */
    private static LocalDateTime parseDateTime(String datePart, String expectedPrefix) throws InvalidDateException {
        datePart = datePart.trim();
        if (!datePart.startsWith(expectedPrefix)) {
            throw new InvalidDateException("Invalid date format: " + datePart);
        }
        String dateString = datePart.substring(expectedPrefix.length()).trim();
        try {
            return TimeConverter.convertTime(dateString);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid date: " + dateString);
        }
    }

    /**
     * Parses the end date and time part of the event string.
     *
     * @param endDatePart   the part of the string containing the end date and time
     * @param startDateTime the start date and time of the event
     * @return the parsed LocalDateTime object for the end date
     */
    private static LocalDateTime parseEndDateTime(String endDatePart,
                                                  LocalDateTime startDateTime) throws InvalidDateException {
        String endDate = endDatePart.substring(2).trim();

        String[] endDates = endDate.split(" ");
        try {
            LocalDateTime endDateTime = (endDates.length == 1)
                    ? TimeConverter.convertTime(startDateTime.toLocalDate().toString() + " " + endDate)
                    : TimeConverter.convertTime(endDate);
            return endDateTime;
        } catch (DateTimeParseException e) {
            throw new InvalidDateException("Invalid enddate");
        }
    }

    /**
     * Returns a String representation of the Events instance in a format suitable for saving to storage.
     * The format includes the task type, completion status, description, start date, and end date.
     *
     * @return a String representing the saved format of the event
     */
    @Override
    public String saveFormat() {
        return "E | " + (super.isDone ? "1 | " : "0 | ")
                + description + " | " + startDate + " | " + endDate;
    }

    /**
     * Updates the start and end times of the event based on the provided string.
     * The string can update both the "from" (start date) and "to" (end date) times,
     * or either one individually.
     *
     * @param time the string specifying the time update
     */
    @Override
    public void updateTime(String time) throws UpdateCommandException {
        try {
            if (time.contains("from") && time.contains("to")) {
                String[] times = time.split("/");
                this.startDate = TimeConverter.convertTime(times[0].substring(5).trim());
                String endDate = times[1].substring(3).trim();
                String[] endDates = endDate.split(" ");
                LocalDateTime endDateTime = (endDates.length == 1)
                        ? TimeConverter.convertTime(startDate.toLocalDate().toString() + " " + endDate)
                        : TimeConverter.convertTime(endDate);
                this.endDate = endDateTime;
            } else if (time.contains("from")) {
                // no to only from
                this.startDate = TimeConverter.convertTime(time.substring(5).trim());
            } else if (time.contains("to")) {
                // no from only to
                this.endDate = TimeConverter.convertTime(time.substring(3).trim());
            } else {
                throw new UpdateCommandException("Invalid update Command");
            }
        } catch (DateTimeParseException e) {
            throw new UpdateCommandException("Invalid update Command");
        }
    }
}

