public class AstaException extends Exception {
    public AstaException(String message) {
        super(message);
    }

    public static void handleInvalidCommand() throws AstaException {
        throw new AstaException("Unfortunately, Asta don't know what that " + "means...");
    }

    public static void handleEmptyTodoDescription() throws AstaException {
        throw new AstaException("Unfortunately, Asta can't add a todo without a description...");
    }

    public static void handleInvalidMarkTaskNumber() throws AstaException {
        throw new AstaException("Unfortunately, the task number provided doesn't seem valid...");
    }

    public static void handleInvalidUnmarkTaskNumber() throws AstaException {
        throw new AstaException("Unfortunately, the task number provided doesn't seem valid...");
    }

    public static void handleInvalidDeadlineInput() throws AstaException {
        throw new AstaException("Unfortunately, Asta needs both a description and a 'by' time for the deadline...");
    }

    public static void handleInvalidEventInput() throws AstaException {
        throw new AstaException("Unfortunately, Asta needs both a description and 'from' and 'to' times for the event...");
    }

    public static void handleInvalidTaskNumberFormat(String command) throws AstaException {
        throw new AstaException("Unfortunately, Asta needs a valid task number to " + command + "...");
    }
}
