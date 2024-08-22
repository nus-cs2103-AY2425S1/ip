public class Todo extends Task {

    public boolean done;
    public String text;

    public Todo(String text) {
        super(text);
        this.text = text;
        this.done = false;
    }

    public void setDone(boolean toset) {
        this.done = toset;
    }

    public String toString() {
        String str = "";
        if (this.done) {
            str = "X";
        } else {
            str = " ";
        }
        return "[T][" + str + "] " + this.text;
    }
}
