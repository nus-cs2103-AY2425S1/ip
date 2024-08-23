package Joseph;
public class Events extends ToDo {
    private String start;
    private String end;

    public Events(String desc, String start, String end) {
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
}
