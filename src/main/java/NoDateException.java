public class NoDateException extends Exception {
    public NoDateException() {
        super("I don't see the correct format of dates given to me. Please ensure you input the date or input it in the correct format!\n" +
                "For deadlines: 'deadline (description) /by (date)'\n" +
                "For events: 'event (description) /from (from date) /to (to date)'");
    }
}
