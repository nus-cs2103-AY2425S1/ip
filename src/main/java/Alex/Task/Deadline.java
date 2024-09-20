package Alex.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.DayOfWeek;

import Alex.Exceptions.AlexException; // Assume you have a custom exception for your chatbot

/**
 * Represents a deadline task.
 */
public class Deadline extends Task {
    public LocalDateTime by;
    private String naturalLanguageDay; // Stores the natural language input like "Sunday"

    /**
     * Constructs a Deadline with the given description and deadline time.
     * @param description The description of the deadline task.
     * @param by The deadline time in the format "yyyy-MM-dd HH:mm" or a natural language date (e.g., "Sunday").
     * @throws AlexException If the input format is invalid.
     */
    public Deadline(String description, String by) throws AlexException {
        super(description);
        this.by = parseDateTime(by);
    }

    /**
     * Gets the type of the task.
     * @return The type of the task (DEADLINE).
     */
    public TaskType getTaskType() {
        return TaskType.DEADLINE;
    }

    /**
     * Gets the string representation of the Deadline task.
     * @return The string representation of the task.
     */
    @Override
    public String toString() {
        if (naturalLanguageDay != null) {
            // If natural language input (like "Sunday") was used, return it directly
            return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + naturalLanguageDay + ")";
        } else {
            // Use a more human-readable date format for normal dates
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM dd yyyy HH:mm");
            return "[D][" + (isDone ? "X" : " ") + "] " + description + " (by: " + by.format(formatter) + ")";
        }
    }

    /**
     * Parses the given date-time string into a LocalDateTime object.
     * @param dateTime The date-time string to parse.
     * @return The parsed LocalDateTime object.
     * @throws AlexException If the input format is invalid.
     */
    private LocalDateTime parseDateTime(String dateTime) throws AlexException {
        try {
            // Try to parse in the strict date-time format first (yyyy-MM-dd HH:mm)
            DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
            return LocalDateTime.parse(dateTime, dateTimeFormatter);
        } catch (DateTimeParseException e) {
            // If parsing as strict date-time format fails, check for natural language input
            return handleNaturalLanguageDate(dateTime);
        } catch (Exception e) {
            // Any other unexpected errors
            throw new AlexException("An error occurred while parsing the date.");
        }
    }

    /**
     * Handles natural language input (e.g., "Sunday") for the date.
     * @param dateTime The natural language date string.
     * @return The parsed LocalDateTime object.
     * @throws AlexException If the natural language input is not valid.
     */
    private LocalDateTime handleNaturalLanguageDate(String dateTime) throws AlexException {
        LocalDateTime now = LocalDateTime.now();
        switch (dateTime.toLowerCase()) {
        case "today":
            this.naturalLanguageDay = "today";
            return now;
        case "tomorrow":
            this.naturalLanguageDay = "tomorrow";
            return now.plusDays(1);
        case "sunday":
            this.naturalLanguageDay = "Sunday";
            return getNextDayOfWeek(DayOfWeek.SUNDAY);
        case "monday":
            this.naturalLanguageDay = "Monday";
            return getNextDayOfWeek(DayOfWeek.MONDAY);
        // ... handle other days of the week similarly ...
        default:
            throw new AlexException("Invalid date input. Please provide a valid date AND time (e.g., yyyy-MM-dd HH:mm), or a valid day (e.g., Sunday).");
        }
    }

    /**
     * Gets the next occurrence of the given day of the week.
     * @param dayOfWeek The day of the week to find.
     * @return The next occurrence of the specified day of the week as a LocalDateTime.
     */
    private LocalDateTime getNextDayOfWeek(DayOfWeek dayOfWeek) {
        LocalDateTime now = LocalDateTime.now();
        int daysToAdd = (dayOfWeek.getValue() - now.getDayOfWeek().getValue() + 7) % 7;
        return now.plusDays(daysToAdd == 0 ? 7 : daysToAdd); // Move to next week if today is the same day
    }
}
