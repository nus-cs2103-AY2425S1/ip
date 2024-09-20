package tasks;

public abstract class Task {
    private String name;
    private boolean hasCompleted = false;

    public Task(String name) {
        this.name = name;
    }

    public void complete() {
        this.hasCompleted = true;
    }

    public void uncomplete() {
        this.hasCompleted = false;
    }

    public String getSaveFormat() {
        String s = this.hasCompleted ? "1 | " : "0 | ";
        return s + this.name;
    }

    public boolean isDuring(String date) {
        return false;
    }

    public boolean hasInName(String text) {
        return this.name.contains(text);
    }

    @Override
    public String toString() {
        String completedBox = this.hasCompleted ? "[X] " : "[ ] ";
        return completedBox + this.name;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj instanceof Task temp) {
            return this.hasCompleted == temp.hasCompleted && this.name == temp.name;
        }
        return false;
    }
}
