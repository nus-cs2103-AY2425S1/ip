package Joseph;
public class JEvent extends Task {
    private final String start;
    private final String end;

    public JEvent (String desc, String start, String end) {
        super(desc);
        this.start = start;
        this.end = end;
    }

    public String getStart() {
        return this.start;
    }

    public String getEnd() {
        return this.end;
    }

    @Override
    public String getDesc() {
        return super.getDesc() + "start: " + this.start + "end: "  + this.end;
    }
}
