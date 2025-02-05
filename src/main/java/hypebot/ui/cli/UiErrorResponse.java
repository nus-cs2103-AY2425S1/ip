package hypebot.ui.cli;

import static hypebot.common.Messages.ERROR_INTRO;

import hypebot.main.HypeBot;
import hypebot.ui.gui.UiGuiMainWindow;

/**
 * Represents the {@code UiErrorResponse} created and shown to user interface
 * at {@link UiGuiMainWindow} instead of a {@link UiResponse}
 * when an error occurs during a {@link HypeBot} session.
 * <p>A child of {@link UiResponse}.</p>
 *
 * @author Youngseo Park (<a href="https://github.com/youngseopark05">@youngseopark05</a>)
 */
public class UiErrorResponse extends UiResponse {
    /**
     * Takes in a {@link String} error message specifying what kind of error occurred
     * to user and creates a new {@code UiErrorResponse} that shows the error message with the
     * default error message prefix.
     *
     * @param message {@link String} error message specifying what kind of error occurred.
     */
    public UiErrorResponse(String message) {
        super(ERROR_INTRO + message);
    }
}
