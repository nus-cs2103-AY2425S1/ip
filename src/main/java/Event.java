public class Event extends Task {
    String begin, end;

    public Event(String description, String begin, String end) {
        super(description);
        this.begin = begin;
        this.end = end;
    }

    /**
     * Returns the start of the event.
     * @return The start of the event
     */
    public String getBegin() {
        return this.begin;
    }

    /**
     * Returns the end of the event.
     * @return The end of the event
     */
    public String getEnd() {
        return this.end;
    }

    @Override
    public String toString() {
        String prev = super.toString();
        return "[E" + prev.substring(2) + " (begins: " + begin + ", ends: " + end + ")";
    }
}
