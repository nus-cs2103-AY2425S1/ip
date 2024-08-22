public class Deadline extends Task {
    public boolean done;
    public String text;
    public String date;

    public Deadline(String text, String date) {
        super(text);
        this.text = text;
        date = date.split("by ")[1];
        this.date = date;
    }

    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String toString() {
        String str = "";
        if (done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[D][" + str + "] " + text + " (by: " + date + ")";
    }
}
