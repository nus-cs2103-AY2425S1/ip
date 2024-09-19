package hypebot.task;

import java.io.File;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import hypebot.command.HappeningCommand;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents an {@code Event} type {@link Task} with
 * a {@link LocalDateTime} start time and an end time.
 * <p>A child of {@link Task}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see LocalDateTime
 * @see LocalDate
 * @see DateTimeFormatter
 */
public class Event extends Task {
    /** {@link DateTimeFormatter} of how an event time is encoded to a {@link File}. */
    private static final DateTimeFormatter EVENT_TIME_FORMATTER_FILE = DateTimeFormatter.ofPattern("yyyy-MM-dd HHmm");

    /** {@link DateTimeFormatter} of how event time is encoded to a {@link UiGuiMainWindow}. */
    private static final DateTimeFormatter EVENT_TIME_FORMATTER_UI = DateTimeFormatter.ofPattern("MMM d yyyy HH:mm");
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    /**
     * Takes in a {@link String} name, a {@link LocalDateTime} start time and end time
     * and creates an {@code Event} with the specified name, start time, and end time.
     *
     * @param name    {@link String} name of the {@code Event}.
     * @param startTime {@link LocalDateTime} start time of the {@code Event}.
     * @param endTime   {@link LocalDateTime} end time of the {@code Event}.
     */
    public Event(String name, LocalDateTime startTime, LocalDateTime endTime) {
        super(name);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    /**
     * Takes in a {@link LocalDate} representing a search date from a {@link HappeningCommand}
     * and returns whether the {@code Event} is happening on the given date.
     *
     * @param date {@link LocalDate} representing a search date.
     * @return Whether the {@code Event} is happening on the given date.
     */
    @Override
    public boolean isHappeningOn(LocalDate date) {
        LocalDate startDate = startTime.toLocalDate();
        LocalDate endDate = endTime.toLocalDate();
        return (date.isEqual(startDate) || date.isAfter(startDate))
                && (date.isEqual(endDate) || date.isBefore(endDate));
    }

    /**
     * Returns the {@link String} description of {@code Event} to append to a {@link File}.
     * <p>Should be in this form: "E , {0 if not complete, 1 if complete} ,
     * {{@code name}} , {{@code startTime}, {{@code endTime}} as specified by
     * {@code EVENT_TIME_FORMATTER_FILE}}".</p>
     *
     * @return {@link String} description of {@code Event} to append to the save {@link File}
     *         on the user's local computer.
     */
    @Override
    public String toFileString() {
        return "E , %s , %s , %s\n".formatted(
                super.toFileString(),
                startTime.format(EVENT_TIME_FORMATTER_FILE),
                endTime.format(EVENT_TIME_FORMATTER_FILE));
    }

    /**
     * Returns the {@link String} representation of the {@code Event} as shown
     * to the user on the {@link UiGuiMainWindow}.
     * <p>Is in this form: "[E][(X if complete)] {{@code name}} (from: {{@code startTime}
     * to: {{@code endTime}})" as specified by {@code EVENT_TIME_FORMATTER_UI}}.</p>
     *
     * @return {@link String} representation of {@code Event} as shown on GUI.
     */
    @Override
    public String toString() {
        return "[E]%s (from: %s to: %s)".formatted(
                super.toString(),
                startTime.format(EVENT_TIME_FORMATTER_UI),
                endTime.format(EVENT_TIME_FORMATTER_UI));
    }

    /**
     * Takes in an {@link Object} and returns if the {@link Object} being compared
     * is a duplicate of this {@code Event}.
     *
     * @param obj {@link Object} to be compared.
     * @return If this {@code Event} is a duplicate of the other.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Event event) {
            // Checks whether both share the same name, start time, end time.
            return super.equals(event) && this.startTime.equals(event.startTime) && this.endTime.equals(event.endTime);
        }
        return false;
    }
}
