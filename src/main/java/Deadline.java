public class Deadline extends Task {
    private String by;

    public Deadline(String name, String by) {
        super(name);
        this.by = by;
    }

    public static Deadline getDeadlineFromInput(String input) throws InvalidTaskFormatException {
        if (input.length() < 9) {
            throw new InvalidTaskFormatException("Deadline");
        }
        
        String[] parts = input.substring(9).split(" /by ");
        if (parts.length != 2) {
            throw new InvalidTaskFormatException("Deadline");
        }

        return new Deadline(parts[0], parts[1]);
    }

    @Override
    public String toString() { 
        return "[D]" + super.toString() + " (by: " + this.by + ")"; 
    }
}
