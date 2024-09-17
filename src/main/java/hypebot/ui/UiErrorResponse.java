package hypebot.ui;

import static hypebot.common.Messages.ERROR_INTRO;

public class UiErrorResponse extends UiResponse {
    public UiErrorResponse(String message) {
        super(ERROR_INTRO + message);
    }
}
