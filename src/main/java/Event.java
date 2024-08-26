public class Event extends Task {
    private final String start;
    private final String end;

    public Event(String t, String s, String e) {

        super(t);
        this.start = s;
        this.end = e;
        this.type = "[E]";
    }

    public static void load(String[] arr) {
        new Event(arr[1], arr[2], arr[3]);
    }

    @Override
    public String saveFileFormat() {
        return "E | " + this.getTask() + " | " + this.start + " | " + this.end;
    }

    @Override
    public String toString() {
        return super.toString() + " (From: " + this.start + " To: " + this.end + ")";
    }
}