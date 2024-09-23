package yap.task;

import yap.ui.InputException;

/**
 * An exception which is thrown by a wrong input format of event.
 */
public class EventException extends InputException {

    /**
     * The different causes of an event exception.
     */
    public enum EventExceptionType {
        NOFROM,
        NOTO,

    }

    /**
     * Constructs a EventException with a generic message.
     */
    public EventException() {
        super("Incorrect usage of \"event\"\n"
                + "Correct usage: event event_name /from start_time /to end_time");
    }

    /**
     * Constructs a EventException with a specific message based on the reason for the exception.
     */
    public EventException(EventExceptionType eventExceptionType) {
        super(getMessageForExceptionType(eventExceptionType));
    }

    private static String getMessageForExceptionType(EventExceptionType eventExceptionType) {
        String correctUsage = "Correct usage: \nevent task_description /from yyyy-mm-dd /to yyyy-mm-dd";
        switch (eventExceptionType) {
        case NOFROM -> {
            return "Incorrect usage of \"event\", no /from start_time (and potentially no /to end_time)\n"
                    + correctUsage;
        }
        case NOTO -> {
            return "Incorrect usage of \"event\", no /to end_time\n" + correctUsage;
        }
        default -> {
            return "Incorrect usage of \"event\"\nCorrect usage: event event_name /from start_time /to end_time"
                    + correctUsage;
        }
        }
    }
}
