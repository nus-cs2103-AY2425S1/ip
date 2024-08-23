public class Deadlines extends Task{
    String time;
    Deadlines(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (" + this.time + ")";
    }
}
