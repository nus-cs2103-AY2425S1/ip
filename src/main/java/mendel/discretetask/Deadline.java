package mendel.discretetask;

import mendel.mendelexception.ConditionalExceptionHandler;
import mendel.mendelexception.MendelException;

public class Deadline extends Task {
    private final String rawDescription;

    public Deadline(String description) {
        super(description);
        this.rawDescription = description;
        this.parseDescription();
    }

    private void parseDescription() {
        this.handleError();
        String[] slashSegments = this.rawDescription.split(" /by ");
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
        reformattedMsg += String.format(" (by: %s)", endMsg);
        super.editMessage(reformattedMsg);
    }

    private void handleError() throws MendelException {
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
