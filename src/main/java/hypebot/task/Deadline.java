package hypebot.task;

import java.io.File;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import hypebot.command.HappeningCommand;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents a {@code Deadline} type {@link Task} with a {@link LocalDate} due date.
 * <p>A child of {@link Task}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see LocalDate
 * @see DateTimeFormatter
 */
public class Deadline extends Task {
    /** {@link DateTimeFormatter} of how due date is encoded to a {@link File}. */
    private static final DateTimeFormatter DUE_DATE_FORMATTER_FILE = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    /** {@link DateTimeFormatter} of how due date is outputted to {@link UiGuiMainWindow}. */
    private static final DateTimeFormatter DUE_DATE_FORMATTER_UI = DateTimeFormatter.ofPattern("MMM d yyyy");

    /** {@link LocalDate} representation of this {@code Deadline}'s due date. */
    private LocalDate dueDate;

    /**
     * Takes in a {@link String} name and a {@link LocalDate} due date,
     * and creates a {@code Deadline} with the specified name and due date.
     *
     * @param name     {@link String} name of the {@code Deadline}.
     * @param dueDate {@link LocalDate} due date of the {@code Deadline}.
     */
    public Deadline(String name, LocalDate dueDate) {
        super(name);
        this.dueDate = dueDate;
    }

    /**
     * Takes in a {@link LocalDate} representing a search date from a {@link HappeningCommand}
     * and returns whether the {@code Deadline} is happening on the given date.
     *
     * @param date {@link LocalDate} representing a search date.
     * @return Whether the {@code Deadline} is happening on the given date.
     */
    @Override
    public boolean isHappeningOn(LocalDate date) {
        return dueDate.isEqual(date);
    }

    /**
     * Returns the {@link String} description of {@code Deadline} to append to a {@link File}.
     * <p>Should be in this form: "D , {0 if not complete, 1 if complete} ,
     * {{@code name}} , {{@code dueDate} as specified by {@code DUE_DATE_FORMATTER_FILE}}".</p>
     *
     * @return {@link String} description of {@code Deadline}
     *         to append to the save {@link File} on the user's local computer.
     */
    @Override
    public String toFileString() {
        return "D , %s , %s\n".formatted(super.toFileString(), dueDate.format(DUE_DATE_FORMATTER_FILE));
    }

    /**
     * Returns the {@link String} representation of the {@code Deadline} as shown
     * to the user on the {@link UiGuiMainWindow}.
     * <p>Is in this form: "[D][(X if complete)] {{@code name}} (by: {{@code dueDate}
     * as specified by {@code DUE_DATE_FORMATTER_UI}})".</p>
     *
     * @return {@link String} representation of {@code Deadline} as shown on GUI.
     */
    @Override
    public String toString() {
        return "[D]%s (by: %s)".formatted(super.toString(), dueDate.format(DUE_DATE_FORMATTER_UI));
    }

    /**
     * Takes in an {@link Object} and returns if the {@link Object} being compared
     * is a duplicate of this {@code Deadline}.
     *
     * @param obj {@link Object} to be compared.
     * @return If this {@code Deadline} is a duplicate of the other.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Deadline deadline) {
            // Checks whether both share the same name and due date.
            return super.equals(deadline) && this.dueDate.isEqual(deadline.dueDate);
        }
        return false;
    }
}
