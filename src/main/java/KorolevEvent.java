public class KorolevEvent extends KorolevTask{
    private String deadline;
    private String tag;

    public KorolevEvent(String name, String date) {
        super(name);
        this.deadline = date;
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
