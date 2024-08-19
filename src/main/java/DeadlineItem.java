public class DeadlineItem extends TodoItem{

    private String deadline;

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
