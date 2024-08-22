public abstract class Task {
    private String name;
    private boolean completed;
    private int index;
    private String taskType;

    public Task(String name, boolean completed, int index) {
        this.name = name;
        this.completed = completed;
        this.index = index;
    }

    public String checkMark() {
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

    public String getName() {
        return this.name;
    }

    public int getIndex() {
        return this.index;
    }

    @Override
    public abstract String toString();
}
