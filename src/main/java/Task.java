public class Task {
    private int num;
    private boolean done = false;
    private String name;

    public Task(int num, String name) {
        this.num = num;
        this.name = name;
    }

    public String stringify() {
        String str = "";
        str += (this.num + ".");
        if (done) {
            str += "[X]";
        } else {
            str += "[ ]";
        }
        str += (" " + this.name + "\n");
        return str;
    }

    public void markAsDone() {
        this.done = true;
    }

    public void markAsUndone() {
        this.done = false;
    }
}
