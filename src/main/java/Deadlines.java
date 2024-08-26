public class Deadlines extends Task{
<<<<<<< HEAD
    private String time;
=======
    String time;
>>>>>>> origin/master
    Deadlines(String content, String time) {
        super(content);
        this.time = time;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.time + ")";
    }
}
