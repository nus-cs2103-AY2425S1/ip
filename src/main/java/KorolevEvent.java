import utils.EventParser;
public class KorolevEvent extends KorolevTask{
    private String deadline;
    private String tag;

    public KorolevEvent(String name) {
        super(EventParser.parseName("event", "/from", name));
        this.deadline = EventParser.parseDate(name);
        this.tag = "E";
    }

    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.tag + "]";
        String deadlines = this.deadline;

        return head + base + " (" + deadlines + ")";
    }
}
