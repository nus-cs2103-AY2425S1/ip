public class Deadline extends Task {
    private String by;
    public Deadline(String description, String by) {
        super(description);
        this.by = by;
    }

    public Deadline(String description, String by, boolean isDone) {
        super(description, isDone);
        this.by = by;
    }

    public static Task from(String text) {
        String[] parameters = text.split("\\s\\|\\s");
        return new Deadline(parameters[2], parameters[3], parameters[1].equals("1"));

    }

    @Override
    public String toText() {
        return String.format("D | %s | %s | %s", super.isDone ? 1 : 0, super.description, this.by);
    }

    @Override
    public String toString() {
        return String.format("[D]%s (by: %s)", super.toString(), this.by);
    }

}
