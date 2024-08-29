package grok;

public class Deadline extends Task {
    protected String due;

    public Deadline(String description, String due) throws GrokInvalidUserInputException {
        super(description);
        this.due = due;
    }

    public Deadline(String description, String due, Boolean isDone) throws GrokInvalidUserInputException {
        super(description, isDone);
        this.due = due;
    }

    @Override
    public String serialize() {
        return String.join(" | ", "D", super.serialize(), due);
    }


    @Override
    public String toString() {
        return "[E] " + super.toString() + "(by: " + due + ")";
    }
}
