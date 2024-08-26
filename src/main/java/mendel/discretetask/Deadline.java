package mendel.discretetask;

import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

public class Deadline extends Task {
    public Deadline(String description, String by) {
        super(String.format("%s (by: %s)", description, by));
    }

    public static Deadline of(String rawDescription) {
        String[] descriptionLst = parseDescription(rawDescription);
        return new Deadline(descriptionLst[0], descriptionLst[1]);
    }

    public static Deadline loadOf(boolean mark, String description, String by) {
        Deadline initObj = new Deadline(description, by);
        if (mark) {
            initObj.markAsDone();
        } else {
            initObj.markAsUnDone();
        }
        return initObj;
    }

    private static String[] parseDescription(String rawDescription) {
        handleError(rawDescription);
        String[] slashSegments = rawDescription.split(" /by ");
        String[] mainMessage = slashSegments[0].split(" ");
        String endMsg = slashSegments[1];
        String reformattedMsg = "";
        for (int i = 1; i < mainMessage.length; i++) {
            if (i == mainMessage.length - 1) {
                reformattedMsg += mainMessage[i];
            } else {
                reformattedMsg += mainMessage[i] + " ";
            }
        }
//        reformattedMsg += String.format(" (by: %s)", endMsg);
        return new String[]{reformattedMsg, endMsg};
    }

    private static void handleError(String rawDescription) throws MendelException {
        String[] slashSegments = rawDescription.split(" /by ");
        String[] misplacedSegments = rawDescription.split("/by");
        String[] mainMessage = slashSegments[0].split(" ");
        ConditionalExceptionHandler.of()
                .conditionTriggerException(mainMessage.length == 1 && slashSegments.length < 2,
                        "OOPS! deadline needs more details.\nAdd description.")
                .conditionTriggerException(misplacedSegments.length != slashSegments.length,
                        "OOPS! deadline due wrongly formatted\nPlease add spaces around /by")
                .conditionTriggerException(mainMessage.length == 1,
                        "OOPS! deadline description cannot be empty.\nAdd description.")
                .conditionTriggerException(slashSegments.length < 2,
                        "OOPS! deadline due cannot be empty.\nPlease indicate a due.")
                .conditionTriggerException(slashSegments.length > 2,
                        "OOPS! I am unsure of due.\nPlease specify only one due.");
        String endMsg = slashSegments[1];
        ConditionalExceptionHandler.of()
                .conditionTriggerException(endMsg.isEmpty(), "OOPS! I am unsure of due.\nPlease specify a due.");
    }

    @Override
    public String toString() {
        return String.format("[D]%s", super.toString());
    }
}
