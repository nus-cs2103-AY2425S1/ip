package hypebot.task;

import java.io.File;
import java.time.LocalDate;
import java.util.regex.Pattern;

import hypebot.command.FindCommand;
import hypebot.command.HappeningCommand;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents a {@code Task} which all the different types of {@code Task}s inherit from.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see ToDo
 * @see Deadline
 * @see Event
 */
public class Task {
    /** Whether the {@code Task} is complete. */
    private boolean isComplete = false;
    /** Name of the {@code Task}. */
    private final String name;

    /**
     * Takes in a {@link String} name
     * and creates a new {@code Task} with the specified name.
     *
     * @param name {@link String} name of the {@code Task}.
     */
    public Task(String name) {
        this.name = name.stripTrailing();
    }

    /**
     * Returns the {@code name} of the {@code Task}.
     *
     * @return {@link String} {@code name} of the {@code Task}.
     */
    public String getName() {
        return name;
    }

    /**
     * Takes in a regex {@link Pattern} of search keywords from {@link FindCommand}
     * and returns whether the {@code Task}'s {@code name} contains any of the keywords.
     *
     * @param searchQuery {@link Pattern} of search keywords.
     * @return Whether the {@code Task}'s {@code name} contains any of the keywords.
     */
    public boolean nameContains(Pattern searchQuery) {
        return searchQuery.matcher(name.toLowerCase()).find();
    }

    /**
     * Returns whether the {@code Task} {@code isComplete}.
     *
     * @return Whether the {@code Task} {@code isComplete}.
     */
    public boolean isComplete() {
        return isComplete;
    }

    /**
     * Marks {@code Task} as complete by changing {@code isComplete} to {@code true}.
     */
    public void mark() {
        this.isComplete = true;
    }

    /**
     * Marks {@code Task} as incomplete by changing {@code isComplete} to {@code false}.
     */
    public void unmark() {
        this.isComplete = false;
    }

    /**
     * Takes in a {@link LocalDate} representing a search date from a {@link HappeningCommand}
     * and returns whether the {@code Task} is happening on the given date.
     *
     * @param date LocalDate representing a search date.
     * @return {@code false} - {@code Task} base class does not have a date associated.
     */
    public boolean isHappeningOn(LocalDate date) {
        return false;
    }

    /**
     * Returns the {@link String} description of the {@code Task}
     * to append to a {@link File}.
     * <p>Should be in this form: "{0 if not complete, 1 if complete} , {{@code name}}".</p>
     *
     * @return {@link String} description of {@code Task} to append to the save {@link File}
     *         on the user's local computer.
     */
    public String toFileString() {
        return "%d , %s".formatted(isComplete ? 1 : 0, name);
    }

    /**
     * Returns the {@link String} representation of the {@code Task}
     * as shown to the user on the {@link UiGuiMainWindow}.
     * <p>Should be in this form: "[{X if complete}] {{@code name}}".</p>
     *
     * @return {@link String} representation of {@code Task} as shown on GUI.
     */
    @Override
    public String toString() {
        return "[%s] %s".formatted(isComplete ? "X" : " ", name);
    }

    /**
     * Takes in an {@link Object} and returns if the {@link Object} being compared
     * is a duplicate of this {@code Task}.
     *
     * @param obj {@link Object} to be compared.
     * @return If this {@code Task} is a duplicate of the other.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof Task task) {
            return this.name.equals(task.name);
        }
        return false;
    }
}
