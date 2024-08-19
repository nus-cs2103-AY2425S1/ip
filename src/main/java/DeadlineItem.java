/**
 * TodoItem represents a to-do entry with a specified deadline
 */
public class DeadlineItem extends TodoItem{
    /** Deadline that specifies when the to-do is due **/
    private final String deadline;

    /**
     * Creates a new to-do item with the specified content and deadline
     *
     * @param content The task description of the to-do item
     * @param deadline The deadline of the to-do item
     */
    public DeadlineItem(String content ,String deadline) {
        super(content);
        this.deadline = deadline.trim();
    }

    @Override
    public String toString() {
        String baseString = super.toString();
        return String.format("%s (by: %s)", baseString.replaceFirst("T", "D"), this.deadline);

    }

}
