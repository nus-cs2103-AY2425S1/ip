public class Event extends Task {
    String begin, end;

    public Event(String description, String begin, String end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    @Override
    public String toString() {
        String prev = super.toString();
        return "[E" + prev.substring(2) + " (begins: " + begin + ", ends: " + end + ")";
    }
}
