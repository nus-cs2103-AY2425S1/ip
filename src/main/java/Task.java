public abstract class Task {
    String description;
    boolean isMarked;

    public Task(String description, boolean isMarked) {
        this.description = description;
        this.isMarked = isMarked;
    }

    public void markTask() {
        this.isMarked = true;
    }

    public void unmarkTask() {
        this.isMarked = false;
    }

    public boolean isMarked() {
        return isMarked;
    }

    public abstract String printTaskOnList();

    public abstract String toFileFormat();

}
