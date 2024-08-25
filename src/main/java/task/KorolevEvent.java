package task;

public class KorolevEvent extends KorolevTask {
    private String duration;
    private String tag;

    public KorolevEvent(String name, String date) {
        super(name);
        this.duration = date;
        this.tag = "E";
    }

    public KorolevEvent(String name, String start, String end) {
        super(name);
        this.duration = "from: " + start + " " + "end: " + end;
        this.tag = "E";
    }

    @Override
    public String toString() {
        String base = super.toString();
        String head = "[" + this.tag + "]";
        String deadlines = this.duration;

        return head + base + " (" + deadlines + ")";
    }
}
