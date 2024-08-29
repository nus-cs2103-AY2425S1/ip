public class Task {
    protected String description;
    protected boolean isdone;

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

    public String getDescription() {
        return this.description;
    }

    public String getStorageRepresentation() {
        return "";
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