package rizzler.command;

/**
 * Represents abstract commands within the Rizzler.
 * Enables further subclassing for specific commands.
 */
public abstract class Command {
    private String textInput;
    private boolean shouldEnd;

    Command() {
        this("");
    }
    Command(String textInput) {
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
