public abstract class Task {
    private String name;
    private boolean completed;
    private String taskType;

    public Task(String name, boolean completed) {
        this.name = name;
        this.completed = completed;
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

    @Override
    public abstract String toString();
}
