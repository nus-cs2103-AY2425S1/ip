package mendel.discretetask;

import mendel.mendelexception.MendelException;

public class Event extends Task {
    private final String rawDescription;

    public Event(String description) {
        super(description);
        this.rawDescription = description;
        this.parseDescription();
    }

    private void parseDescription() {
        this.handleError();
        String[] slashSegments = this.rawDescription.split(" /from ");
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
        super.editMessage(reformattedMsg);
    }

    private void handleError() {
        String[] slashSegments = this.rawDescription.split(" /from ");
        String[] mainMessage = slashSegments[0].split(" ");
        if (slashSegments.length < 2 && slashSegments[0].equals("event")) {
            throw new MendelException("OOPS! event needs more details.\nAdd description.");
        } else if (slashSegments.length < 2) {
            if (this.rawDescription.split("/from").length != slashSegments.length) {
                throw new MendelException("OOPS! deadline from wrongly formatted.\nPlease add spaces around /from.");
            } else {
                throw new MendelException("OOPS! event start cannot be empty.\nPlease indicate a start.");
            }
        } else if (slashSegments.length > 2) {
            throw new MendelException("OOPS! I am unsure of start.\nPlease specify only one start.");
        } else if (mainMessage.length == 1) {
            throw new MendelException("OOPS! event description cannot be empty.\nAdd description.");
        } else if (slashSegments[1].split(" /to ").length != slashSegments[1].split("/to").length) {
            throw new MendelException("OOPS! deadline to wrongly formatted.\nPlease add spaces around /to.");
        }  else if (slashSegments[1].split(" /to ").length < 2) {
            throw new MendelException("OOPS! I am unsure of end.\nPlease specify an end.");
        } else if (slashSegments[1].split(" /to ").length > 2) {
            throw new MendelException("OOPS! I am unsure of end.\nPlease specify only one end.");
        }
        String startMsg = slashSegments[1].split(" /to ")[0];
        String endMsg = slashSegments[1].split(" /to ")[1];
        if (startMsg.isEmpty() && endMsg.isEmpty()) {
            throw new MendelException("OOPS! I am unsure of start and due.\nPlease specify a start and due.");
        } else if (startMsg.isEmpty()) {
            throw new MendelException("OOPS! I am unsure of due.\nPlease specify a due.");
        } else if (endMsg.isEmpty()) {
            throw new MendelException("OOPS! I am unsure of due.\nPlease specify a due.");
        }
    }

    @Override
    public String toString() {
        return String.format("[E]%s", super.toString());
    }
}
