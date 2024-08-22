class Deadline extends Task {
    private String deadline;

    public static String format = "deadline <description> /by <date>";

    public Deadline(String description, String by) {
        super(description);
        this.deadline = by;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + deadline + ")";
    }
}
