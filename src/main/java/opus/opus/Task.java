package opus;

public abstract class Task {
    protected String description;
    protected boolean isDone;

    public Task(String description) {
        this.description = description;
        this.isDone = false;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public boolean isDone() {
        return isDone;
    }

    public abstract String toSaveFormat();

    public static Task fromFileFormat(String fullLine) {
        String[] parts = fullLine.split("\\|");

        if (parts[0].equals("T")) {
            return ToDo.fromFileFormat(fullLine);
        }
        if (parts[0].equals("D")) {
            return Deadline.fromFileFormat(fullLine);
        }
        if (parts[0].equals("E")) {
            return Event.fromFileFormat(fullLine);
        }

        return null;
    }
}
