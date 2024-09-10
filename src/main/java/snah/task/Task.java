package snah.task;

/**
 * Class to handle the tasks of the chatbot
 */
public class Task {

    private boolean isDone = false;
    private String description;

    public Task(String description) {
        this.description = description;
    }

    public void markAsDone() {
        this.isDone = true;
    }

    public void unmarkAsDone() {
        this.isDone = false;
    }

    public boolean isDone() {
        return isDone;
    }

    public String printStatus() {
        return String.format("[%s]", isDone ? "X" : " ");
    }

    public String getDescription() {
        return description;
    }

    /**
     * Returns the task in the format to save to file
     * @return Task in the format to save to file
     */
    public String toSaveFile() {
        return String.format("T:%s:%s", isDone() ? "x" : "", getDescription());
    }

    /**
     * Returns true if the task contains the keyword.
     * @param keyword
     * @return boolean
     */
    public boolean contains(String keyword) {
        return getDescription().contains(keyword);
    }

    @Override
    public String toString() {
        return String.format("%s %s", printStatus(), getDescription());
    }

}
