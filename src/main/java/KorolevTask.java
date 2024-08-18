public class KorolevTask {
    private boolean status = false;
    private String name;

    public KorolevTask(String name) {
        this.name = name;
    }

    public void markTask() {
        this.status = true;
    }

    public void unmarkTask() {
        this.status = false;
    }

    @Override
    public String toString() {
        return (this.status ? "[X]" : "[ ]") + " " + this.name;
    }
}
