public abstract class Task {
    private boolean done;
    private final String name;

    public Task(String name) {
        this.name = name;
        this.done = false;
    }

    public static void fromText(String text) {

    }

    public String toText() {
        char status = done ? '1' : '0';
        return status + " | " + name;
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
