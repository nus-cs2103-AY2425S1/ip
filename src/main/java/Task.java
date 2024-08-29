public abstract class Task {
    protected String description;
    protected boolean isDone;
    static final String FILE_PATH = "./data/Darkpool.txt";

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public Task(String description, boolean isDone) {
        this.description = description;
        this.isDone = isDone;
    }

    public void markDone() {
        this.isDone = true;
    }

    public void markUndone() {
        this.isDone = false;
    }

    public abstract String toString();

    public abstract void saveToFile();
}
