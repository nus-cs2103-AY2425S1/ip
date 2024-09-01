package task;

import exceptions.AlreadyCompletedException;
import exceptions.StartAfterEndException;

public abstract class Task {

    private boolean isCompleted = false;
    private String description;

    public Task(String title) {
        this.description = title;
    }

    public static Task of(String data) throws AlreadyCompletedException, StartAfterEndException {
        String[] args = data.split("\\|");
        return switch (args[0]) {
            case "T" -> ToDo.of(args);
            case "D" -> Deadline.of(args);
            case "E" -> Event.of(args);
            default -> null;
        };
    }

    public void complete() throws AlreadyCompletedException {
        if (this.isCompleted) {
            throw new AlreadyCompletedException();
        }
        this.isCompleted = true;
    }

    public String getStatusIcon() {
        return (this.isCompleted ? "X" : " ");
    }

    public abstract String getTypeIcon();

    public String toData() {
        return String.format("%s|%b|%s", this.getTypeIcon(), this.isCompleted, this.description);
    }

    /**
     * Returns<code>true</code>if task's description contains the keyword.
     *
     * @param keyword Keyword to check for.
     * @return <code>true</code>if task's description contains the keyword.
     */
    public boolean descriptionContains(String keyword) {
        String[] description = this.description.split(" ");
        for (String s : description) {
            if (s.equalsIgnoreCase(keyword)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return this.description;
    }
}

