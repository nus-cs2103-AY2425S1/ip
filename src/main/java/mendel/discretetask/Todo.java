package mendel.discretetask;

import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

public class Todo extends Task {
    private Todo(String description) {
        super(description);
    }

    public static Todo of(String rawDescription) {
        String description = parseDescription(rawDescription);
        return new Todo(description);
    }

    private static String parseDescription(String rawDescription) {
        handleError(rawDescription);
        String[] segments = rawDescription.split(" ");
        String reformattedMsg = "";
        for (int i = 1; i < segments.length; i++) {
            if (i == segments.length - 1) {
                reformattedMsg += segments[i];
            } else {
                reformattedMsg += segments[i] + " ";
            }
        }
        return reformattedMsg;
    }

    private static void handleError(String rawDescription) throws MendelException {
        String[] segments = rawDescription.split(" ");
        ConditionalExceptionHandler.of()
                .conditionTriggerException(segments.length == 1,
                        "OOPS! todo description cannot be empty.\nAdd description.");
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
