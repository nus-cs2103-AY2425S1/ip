public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline getDeadlineFromInput(String input) {
        String[] parts = input.substring(9).split(" /by ");

        return new Deadline(parts[0], parts[1]);
    }

    @Override
    public String toString() { 
        return "[D]" + super.toString() + " (by: " + this.by + ")"; 
    }
}
