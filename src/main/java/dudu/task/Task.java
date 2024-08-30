package dudu.task;

public class Task {

    private boolean marked = false;

    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void markCompleted() {
        this.marked = true;
    }

    public void markUncompleted() {
        this.marked = false;
    }

    public String formatString() {
        String status = this.marked ? "1" : "0";
        return String.format("%s | %s", status, this.description);
    }

    @Override
    public String toString() {
        String status = this.marked ? "[X]" : "[ ]";
        return String.format("%s %s", status, this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || !(o instanceof Task)) {
            return false;
        }
        Task other = (Task) o;
        return other.marked == this.marked && this.description.equals(other.description);
    }
}
