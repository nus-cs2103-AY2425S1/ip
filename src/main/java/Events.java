public class Events extends Task{
<<<<<<< HEAD
    private String start, end;
=======
    String start, end;
>>>>>>> origin/master
    Events(String content, String start, String end) {
        super(content);
        this.start = start;
        this.end = end;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString() + " (from: " + this.start + " to: " + this.end + ")";
    }
}
