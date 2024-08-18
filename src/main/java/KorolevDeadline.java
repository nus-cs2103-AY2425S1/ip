import utils.EventParser;

public class KorolevDeadline extends KorolevTask{
    private String deadline;
    private String tag;
    public KorolevDeadline(String name) {
        super(EventParser.parseName("deadline", "/by", name));
        this.deadline = EventParser.parseDate(name);
        this.tag = "D";
    }

    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.tag +"]";
        String deadlines = this.deadline;

        return head + base + " (" + deadlines + ")";
    }
}
