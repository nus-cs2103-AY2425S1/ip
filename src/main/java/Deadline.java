public class Deadline extends Task {

    private String by;

    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    @Override
    public String toString() {
        return String.format("%s (Deadline: by %s) %s", getStatus(), by, getDescription());
    }

}
