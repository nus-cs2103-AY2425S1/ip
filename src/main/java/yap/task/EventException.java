package yap.task;

import yap.ui.InputException;

public class EventException extends InputException {
    public enum EventExceptionType {
        NOFROM,
        NOTO,

    }

    /**
     * Constructs a EventException with a generic message.
     */
    public EventException() {
        super("Incorrect usage of \"event\"\n" +
                "Correct usage: event event_name /from start_time /to end_time");
    }

    /**
     * Constructs a EventException with a specific message based on the reason for the exception.
     */
    public EventException(EventExceptionType eventExceptionType) {
        super(getMessageForExceptionType(eventExceptionType));
    }

    private static String getMessageForExceptionType(EventExceptionType eventExceptionType) {
        switch (eventExceptionType) {
            case NOFROM -> {
                return "Incorrect usage of \"event\", no /from start_time (and potentially no /to end_time)\n";
            }
            case NOTO -> {
                return "Incorrect usage of \"event\", no /to end_time\n";
            }
            default -> {
                return "Incorrect usage of \"event\"\nCorrect usage: event event_name /from start_time /to end_time";
            }
        }
    }
}
