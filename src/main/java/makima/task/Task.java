package makima.task;

public class Task {

    private String name = "";
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public Task(String name, boolean done) {
        this.name = name;
        this.done = done;
    }

    public boolean match(String search) {
        return name.toLowerCase().contains(search.toLowerCase());
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

    public String toFileString() {
        return String.format("%s\n%s\n", name, done);
    }

}
