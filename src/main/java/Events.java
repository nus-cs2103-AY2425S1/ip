public class Events extends Task{
    String time;
    Events(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (" + this.time + ")";
    }
}
