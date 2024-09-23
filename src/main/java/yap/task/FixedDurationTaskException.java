package yap.task;

import yap.ui.InputException;

public class FixedDurationTaskException extends InputException {
    public FixedDurationTaskException() {
        super("You did not input the duration of the task properly!");
    }
}
