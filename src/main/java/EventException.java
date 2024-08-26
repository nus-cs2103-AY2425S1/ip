public class EventException extends InputException {
    public enum EventExceptionType {
        NOFROM,
        NOTO,

    }
    public EventException() {
        super("Incorrect usage of \"event\"\nCorrect usage: event event_name /from start_time /to end_time");
    }

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
