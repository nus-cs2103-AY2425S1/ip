public class Task {

    private String name = "";
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        done = true;
    }

    public void unmark() {
        done = false;
    }

    public String toString() {
        String output = "";

        if (done) {
            output += "[X]";
        } else {
            output += "[ ]";
        }

        return output + " " + name;
    }

}
