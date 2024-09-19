package hypebot.task;

import java.io.File;

import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents a {@code ToDo} type {@link Task}.
 * <p>A child of {@link Task}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class ToDo extends Task {
    /**
     * Takes in a {@link String} name and creates a new {@code ToDo} with the specified name.
     *
     * @param name {@link String} name of the {@code ToDo}.
     */
    public ToDo(String name) {
        super(name);
    }

    /**
     * Returns the {@link String} description of {@code ToDo} to append to a {@link File}.
     * <p>Should be in this form: "T , {0 if not complete, 1 if complete} , {{@code name}}".</p>
     *
     * @return {@link String} description of {@code ToDo}
     *         to append to the save {@link File} on the user's local computer.
     */
    @Override
    public String toFileString() {
        return "T , %s\n".formatted(super.toFileString());
    }

    /**
     * Returns the {@link String} representation of the {@link ToDo} as shown
     * to the user on the {@link UiGuiMainWindow}.
     * <p>Is in this form: "[T][(X if complete)] {{@code name}}".</p>
     *
     * @return {@link String} representation of {@code ToDo} as shown on GUI.
     */
    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    /**
     * Takes in an {@link Object} and returns if the {@link Object} being compared
     * is a duplicate of this {@code ToDo}.
     *
     * @param obj {@link Object} to be compared.
     * @return If this {@code ToDo} is a duplicate of the other.
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        } else if (obj instanceof ToDo toDo) {
            return super.equals(toDo);
        }
        return false;
    }
}
