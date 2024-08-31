package seedu.maxine.task;

public class Deadline extends Task {

    protected String ddl;

    public Deadline() {
        super();
    }

    public Deadline(String description, String ddl) {
        super(description);
        try {
            this.ddl = dateTimeParser(ddl.trim());
        } catch (Exception e) {
            this.ddl = ddl;
        }
    }

    @Override
    public String toString() {
        return "[D]" + getStatusIcon() + description + " (by: " + ddl + ")";
    }

    @Override
    public String writeToFile() {
        return "D" + super.writeToFile() + " / " + ddl;
    }

}

