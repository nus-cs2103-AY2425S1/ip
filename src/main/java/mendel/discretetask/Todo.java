package mendel.discretetask;

import mendel.mendelexception.MendelException;

public class Todo extends Task {
    private final String rawDescription;

    public Todo(String description) {
        super(description);
        this.rawDescription = description;
        this.parseDescription();
    }

    private void parseDescription() {
        this.handleError();
        String[] segments = this.rawDescription.split(" ");
        String reformattedMsg = "";
        for (int i = 1; i < segments.length; i++) {
            if (i == segments.length - 1) {
                reformattedMsg += segments[i];
            } else {
                reformattedMsg += segments[i] + " ";
            }
        }
        super.editMessage(reformattedMsg);
    }

    private void handleError() {
        String[] segments = this.rawDescription.split(" ");
        if (segments.length == 1) {
            throw new MendelException("OOPS! todo description cannot be empty.\nAdd description.");
        }
    }

    @Override
    public String toString() {
        return String.format("[T]%s", super.toString());
    }
}
