public class Deadline extends Task {
    private String due;

    /**
     * Constructor for Deadline.
     * @param description
     * @param due
     */
    public Deadline(String description, String due) {
        super(description.strip());
        StringBuilder str = new StringBuilder(due.strip());
        str.insert(str.indexOf(" "), ':');
        this.due = str.toString();
    }

    @Override
    public String toString() {
        return String.format("[D]%s (%s)", super.toString(), due);
    }
}
