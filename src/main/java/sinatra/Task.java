package sinatra;

public class Task {

    private String content;
    private Boolean status;


    public Task(String content, Boolean status) {
        this.content = content;
        this.status = status;

    }

    public void appendToStorage(String filename) {

        Storage storage = new Storage(filename);
        storage.appendLine(getDataForStorage());


    }

    public void setContent(String content) {
        this.content = content;
    }


    public String getContent() {
        return this.content;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    /**
     * Returns the status of the task.
     *
     * @return the status of the task
     */
    public Boolean isMarked() {
        return this.status;
    }

    /**
     * Returns the status icon of the task.
     *
     * @return "X" if the task is done, otherwise a space
     */
    public String isMarkedIcon() {
        return (status ? "X" : " "); // mark done task with X
    }

    /**
     * Returns the status of the task as a string.
     *
     * @return "True" if the task is done, otherwise "False"
     */
    public String isMarkedString() {
        return (status ? "True" : "False");
    }

    public String getDataForStorage() {
        return "Sinatra.Task:" + content + "," + isMarkedString();
    }


    @Override
    public String toString() {
        return "[" + isMarkedIcon() + "]" + " " + this.content;
    }


}
