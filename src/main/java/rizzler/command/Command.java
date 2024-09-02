package rizzler.command;

import rizzler.Storage;
import rizzler.task.TaskLog;
import rizzler.ui.RizzlerSpeech;

/**
 * Represents abstract commands within the Rizzler.
 * Enables further subclassing for specific commands.
 */
public abstract class Command {
    private final String textInput;
    private boolean shouldEnd;

    protected Command() {
        this("");
    }
    protected Command(String textInput) {
        this.textInput = textInput;
        this.shouldEnd = false;
    }

    public abstract void execute(RizzlerSpeech speech, Storage storage, TaskLog taskLog);

    public boolean shouldEnd() {
        return shouldEnd;
    }

    public void setShouldEnd(boolean shouldEnd) {
        this.shouldEnd = shouldEnd;
    }

    public String getTextInput() {
        return textInput;
    }
}
