package sinatra;

/**
 * Represents a generic task.
 */
public class Task {

    private String content;
    private Boolean status;

    /**
     * Constructs a new Task object with the specified content and status.
     *
     * @param content the content of the task
     * @param status the status of the task
     */
    public Task(String content, Boolean status) {
        this.content = content;
        this.status = status;
    }

    /**
     * Appends the task data to the specified storage file.
     *
     * @param filename the name of the storage file eg tasks.txt
     */
    public void appendToStorage(String filename) {
        Storage storage = new Storage(filename);
        storage.appendLine(getDataForStorage());
    }

    /**
     * Sets the content of the task.
     *
     * @param content the new content of the task
     */
    public void setContent(String content) {
        this.content = content;
    }

    /**
     * Returns the content of the task.
     *
     * @return the content of the task
     */
    public String getContent() {
        return this.content;
    }

    /**
     * Sets the status of the task.
     *
     * @param status the new status of the task
     */
    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task
     */
    public Boolean getStatus() {
        return this.status;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space
     */
    public String getStatusIcon() {
        return (status ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return "True" if the task is done, otherwise "False"
     */
    public String getStatusString() {
        return (status ? "True" : "False");
    }

    /**
     * Returns the data string for storage.
     *
     * @return the data string for storage
     */
    public String getDataForStorage() {
        return "Sinatra.Task:" + content + "," + getStatusString();
    }

    /**
     * Returns the string representation of the task.
     *
     * @return the string representation of the task
     */
    @Override
    public String toString() {
        return "[" + getStatusIcon() + "]" + " " + this.content;
    }
}