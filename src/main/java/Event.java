public class Event extends Task {
    private String startTime;
    private String endTime;

    public Event(String text) {
        super(text.split("/")[0]);
        String[] texts = text.split("/");

        this.startTime = texts[1].substring(texts[1].indexOf(" ") + 1);
        this.endTime = texts[2].substring(texts[2].indexOf(" ") + 1);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() +
                "(from: " + this.startTime + "|to: " + this.endTime + ")";
    }
}
