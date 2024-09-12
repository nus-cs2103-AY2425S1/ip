package beeboo.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import beeboo.components.TimeConverter;
import beeboo.exception.InvalidDateException;
import beeboo.exception.NoDescriptionException;


/**
 * The Events class represents an event task with a start date and an end date. It extends the
 * Tasks class and provides functionality for managing events.
 */
public class Events extends Tasks {

    static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("MMM dd yyyy 'at' hh:mm a");
    private LocalDateTime endDate;
    private LocalDateTime startDate;

    /**
     * Constructs an Events instance with the specified description, start date, and end date.
     *
     * @param description the description of the event
     * @param startDate   the LocalDateTime representing the start date and time of the event
     * @param endDate     the LocalDateTime representing the end date and time of the event
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
     * Returns a String representation of the Events instance, including the description, start date,
     * and end date formatted using the predefined formatter.
     *
     * @return a String representation of the event
     */
    @Override
    public String toString() {
        return typeIcon() + super.toString()
                + "(from: " + FORMATTER.format(startDate) + " to: " + FORMATTER.format(endDate) + ")";
    }

    /**
     * Creates an Events instance from the given text input. The input should include a description and
     * start and end dates in the format "from startDate to endDate". The method parses the input, validates
     * the description and dates, and returns a new Events object.
     *
     * @param text the input text containing the description and event dates
     * @return an Events instance
     * @throws InvalidDateException if the dates are invalid or improperly formatted
     * @throws NoDescriptionException if the description is missing or empty
     */
    public static Events createEvent(String text) throws InvalidDateException, NoDescriptionException {
        int descriptionEnd = text.indexOf('/');
        if (descriptionEnd == -1) {
            throw new InvalidDateException(text);
        }
        String description = text.substring(0, descriptionEnd).trim();

        if (descriptionEnd == -1 || description.isEmpty()) {
            throw new NoDescriptionException("No description");
        }

        String dateSubstring = text.substring(descriptionEnd + 1).trim();
        if (!dateSubstring.startsWith("from")) {
            throw new InvalidDateException(text);
        }

        int startDateEnd = dateSubstring.indexOf('/');
        String startDate = dateSubstring.substring(4, startDateEnd).trim();
        LocalDateTime startDateTime;
        try {
            startDateTime = TimeConverter.convertTime(startDate);
        } catch (DateTimeParseException e) {
            throw new InvalidDateException(text);
        }
        String endDateCommand = dateSubstring.substring(startDateEnd + 1);
        if (!endDateCommand.startsWith("to")) {
            throw new InvalidDateException(text);
        }

        String endDate = endDateCommand.substring(2).trim();
        String[] endDates = endDate.split(" ");
        LocalDateTime endDateTime = (endDates.length == 1)
                ? TimeConverter.convertTime(startDateTime.toLocalDate().toString()
                + " " + endDate)
                : TimeConverter.convertTime(endDate);

        return new Events(description, startDateTime, endDateTime);
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

    //Update 2 from 2024-09-09 1200 /to 2024-09-09 14:00
    //Update 2 from 2024-09-09 1200
    //Update 2 to 2024-09-09 14:00
    @Override
    public void updateTime(String time) {
        if (time.contains("from")) {
            if (time.contains("to")) {
                String[] times = time.split("/");
                this.startDate = TimeConverter.convertTime(times[0].substring(5).trim());
                String endDate = times[1].substring(3).trim();
                String[] endDates = endDate.split(" ");
                LocalDateTime endDateTime = (endDates.length == 1)
                        ? TimeConverter.convertTime(startDate.toLocalDate().toString() + " " + endDate)
                        : TimeConverter.convertTime(endDate);
                this.endDate = endDateTime;
            } else {
                this.startDate = TimeConverter.convertTime(time.substring(5).trim());
            }
        } else {
            this.endDate = TimeConverter.convertTime(time.substring(3).trim());
        }
    }
}
