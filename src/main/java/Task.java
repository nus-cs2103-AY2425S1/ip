public class Task {
    private String name;
    private boolean completed;
    private int index;

    public Task(String name, boolean completed, int index) {
        this.name = name;
        this.completed = completed;
        this.index = index;
    }

    private String checkMark() {
        if (completed) {
            return "X";
        } else {
            return " ";
        }
    }

    public void check() {
        this.completed = true;
    }

    public void uncheck() {
        this.completed = false;
    }

    @Override
    public String toString() {
        return this.index + ". [" + this.checkMark() + "] " + this.name;
    }
}
