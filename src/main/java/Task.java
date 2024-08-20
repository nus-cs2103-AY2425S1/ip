public class Task {
    private boolean done;
    private final String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    @Override
    public String toString() {
        char status = done ? 'X' : ' ';
        return String.format("[%s] %s", status, name);
    }

    public void setDone(boolean status) {
        this.done = status;
    }
}
