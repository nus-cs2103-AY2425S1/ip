public class Task {
    private String name;
    private boolean status;

    public Task(String name, boolean status) {
        this.name = name;
        this.status = status;
    }

    public void setStatus(boolean status) { // setter
        this.status = status;
    }

    @Override
    public String toString() {
        return "[" + (this.status ? "X" : " ") + "] " + this.name;
    }
}
