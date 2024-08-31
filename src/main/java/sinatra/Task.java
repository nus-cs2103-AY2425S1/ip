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

    public Boolean isMarked() {
        return this.status;
    }

    public String isMarkedIcon() {
        return (status ? "X" : " "); // mark done task with X
    }

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
