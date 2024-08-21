public class Task {
    private boolean done = false;
    private String task = "";

    public Task(String task) {
        this.task = task;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println("        " + this);
    }

    public void unmark() {
        this.done = false;
        System.out.println("        " + this);
    }

    @Override
    public String toString() {
        if (done) return "[X] " + this.task;
        else return "[ ] " + this.task;
    }
}