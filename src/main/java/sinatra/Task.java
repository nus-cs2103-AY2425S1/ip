package sinatra;

/**
 * Represents a task in the Sinatra application.
 */
public class Task {

    private String content;
    private Boolean isStatus;

    /**
     * Constructs a new Task with the specified content and status.
     *
     * @param content  the content of the task
     * @param isStatus the status of the task
     */
    public Task(String content, Boolean isStatus) {
        this.content = content;
        this.isStatus = isStatus;
    }

    /**
     * Appends the task data to the storage file.
     *
     * @param filename the name of the file to append the task data
     */
    public void appendToStorage(String filename) {
        Storage storage = new Storage(filename);
        try {
            storage.appendLineToTxtFile(getDataForStorage());
        } catch (SinatraException e) {
            System.out.println("Error writing to file: " + e.getMessage());
        }
    }

    /**
     * Deletes the task data from the storage file.
     *
     * @param filename
     * @param row
     */
    public void deleteFromStorage(String filename, int row) {
        Storage storage = new Storage(filename);
        storage.deleteLineFromFile(row);

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
     * @param isStatus the new status of the task
     */
    public void setStatus(Boolean isStatus) {
        this.isStatus = isStatus;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task
     */
    public Boolean isMarked() {
        return this.isStatus;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space
     */
    public String isMarkedIcon() {
        return (isStatus ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return "True" if the task is done, otherwise "False"
     */
    public String isMarkedString() {
        return (isStatus ? "True" : "False");
    }

    /**
     * Returns the data string for storage.
     *
     * @return the data string for storage
     */
    public String getDataForStorage() {
        return "Sinatra.Task:" + content + "," + isMarkedString();
    }

    /**
     * Returns the string representation of the Task object.
     *
     * @return the string representation of the Task object
     */
    @Override
    public String toString() {
        return "[" + isMarkedIcon() + "]" + " " + this.content;
    }
}
