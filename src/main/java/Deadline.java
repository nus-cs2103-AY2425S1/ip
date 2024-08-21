public class Deadline extends Task {

    protected String ddl;

    public Deadline() {
        super();
    }

    public Deadline(String description, String ddl) {
        super(description);
        this.ddl = ddl;
    }

    @Override
    public String toString() {
        return "[D]" + "[" + getStatusIcon() + "] " + description + " (by: " + ddl + ")";
    }

}

