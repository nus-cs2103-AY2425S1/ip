package ipman.ui;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents a Ui that records all messages that was sent. Cannot read any
 * messages.
 */
public class SpyUi implements Ui {
    private final List<String> recordedMessages = new ArrayList<>();

    public String[] getRecordedMessages() {
        // @@author st0le-reused
        // Reused from https://stackoverflow.com/a/5374336/4428725
        return recordedMessages.toArray(new String[recordedMessages.size()]);
        // @@author
    }

    @Override
    public void showMessage(String message) {
        this.recordedMessages.add("message:" + message);
    }

    @Override
    public void showMessageFormat(String message, Object... args) {
        this.showMessage(String.format(message, args));
    }

    @Override
    public void showError(String message) {
        this.recordedMessages.add("error:" + message);
    }
}
