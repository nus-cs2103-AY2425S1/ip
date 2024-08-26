package mendel.discretetask;

import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

public class Event extends Task {
    private Event(String description) {
        super(description);
    }

    public static Event of(String rawDescription) {
        String description = parseDescription(rawDescription);
        return new Event(description);
    }

    private static String parseDescription(String rawDescription) {
        handleError(rawDescription);
        String[] slashSegments = rawDescription.split(" /from ");
        String[] mainMessage = slashSegments[0].split(" ");
        String startMsg = slashSegments[1].split(" /to ")[0];
        String endMsg = slashSegments[1].split(" /to ")[1];
        String reformattedMsg = "";
        for (int i = 1; i < mainMessage.length; i++) {
            if (i == mainMessage.length - 1) {
                reformattedMsg += mainMessage[i];
            } else {
                reformattedMsg += mainMessage[i] + " ";
            }
        }
        reformattedMsg += String.format(" (from: %s to %s)", startMsg, endMsg);
        return reformattedMsg;
    }

    private static void handleError(String rawDescription) throws MendelException {
        String[] slashSegments = rawDescription.split(" /from ");
        String[] mainMessage = slashSegments[0].split(" ");
        ConditionalExceptionHandler.of()
                .orConditionTriggerException(slashSegments.length < 2)
                .andConditionTriggerException(slashSegments[0].equals("event"),
                        "OOPS! event needs more details.\nAdd description.")
                .andConditionTriggerException(rawDescription.split("/from").length != slashSegments.length,
                        "OOPS! deadline from wrongly formatted.\nPlease add spaces around /from.")
                .conditionTriggerException(slashSegments.length < 2,
                        "OOPS! I am unsure of start.\nPlease specify only one start.")
                .conditionTriggerException(slashSegments.length > 2,
                        "OOPS! event start cannot be empty.\nPlease indicate a start.")
                .conditionTriggerException(mainMessage.length == 1,
                        "OOPS! event description cannot be empty.\nAdd description.")
                .conditionTriggerException(slashSegments[1].split(" /to ").length != slashSegments[1].split("/to").length,
                        "OOPS! deadline to wrongly formatted.\nPlease add spaces around /to.")
                .conditionTriggerException(slashSegments[1].split(" /to ").length < 2,
                        "OOPS! I am unsure of end.\nPlease specify an end.")
                .conditionTriggerException(slashSegments[1].split(" /to ").length > 2,
                        "OOPS! I am unsure of end.\nPlease specify only one end.");
        String startMsg = slashSegments[1].split(" /to ")[0];
        String endMsg = slashSegments[1].split(" /to ")[1];
        ConditionalExceptionHandler.of()
                .conditionTriggerException(startMsg.isEmpty() && endMsg.isEmpty(),
                        "OOPS! I am unsure of start and due.\nPlease specify a start and due.")
                .conditionTriggerException(startMsg.isEmpty(), "OOPS! I am unsure of due.\nPlease specify a due.")
                .conditionTriggerException(endMsg.isEmpty(), "OOPS! I am unsure of due.\nPlease specify a due.");

    }

    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
