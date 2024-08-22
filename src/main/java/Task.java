public class Task {

    private String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }

    public boolean isDone() {
        return this.done;
    }
    public void markAsDone() {
        this.done = true;
    }

    public void unmarkAsDone() {
        this.done = false;
    }

    @Override
    public String toString() {
        String doneString = done ? "X" : " ";
        return String.format("[%1$s] %2$s", doneString, this.name);
    }

}
