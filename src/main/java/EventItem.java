public class EventItem extends TodoItem {

    private String from;
    private String to;

    public EventItem(String content, String from, String to) {
        super(content);
        this.from = from.trim();
        this.to = to.trim();
    }

    @Override
    public String toString() {
        String baseString = super.toString();
        return String.format("%s (from: %s to %s)",
                baseString.replaceFirst("T", "E"),
                this.from, this.to);

    }

}
