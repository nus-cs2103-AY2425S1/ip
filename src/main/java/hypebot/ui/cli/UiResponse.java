package hypebot.ui.cli;

import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code UiResponse} with a message shown to user interface
 * at {@link UiGuiMainWindow}.
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 * @see UiErrorResponse
 */
public class UiResponse {
    /** {@link String} message shown to user interface. */
    private final String message;

    /**
     * Takes in a {@link String} message shown to user interface
     * and creates a new {@code UiResponse}.
     *
     * @param message {@link String} message shown to user interface.
     */
    public UiResponse(String message) {
        this.message = message;
    }

    /**
     * Returns the {@link String} message contained in the {@code UiResponse}.
     */
    public String show() {
        return message;
    }
}
