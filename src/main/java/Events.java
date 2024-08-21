public class Events extends Task {

    private String start;
    private String end;

    public Events(String s, String start, String end) {
        super(s);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        String str = "";
        str = str + "[E]";
        str = str + super.toString();
        str = str + String.format("(from: %s to: %s)",
                start, end);
        return str;
    }
}
