public class Task {
    private String description;
    private boolean isdone;

    public Task(String description) {
        this.description = description;
        this.isdone = false;
    }

    public void markAsDone() {
        this.isdone = true;
    }

    public void markAsUndone() {
        this.isdone = false;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        if (this.isdone) {
            sb.append("[X] ");
        } else {
            sb.append("[ ] ");
        }
        sb.append(this.description);
        return sb.toString();
    }
}