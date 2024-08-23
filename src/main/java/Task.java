
public class Task {

    private String content;
    private Boolean status;

    public Task(String content,Boolean status) {
        this.content = content;
        this.status = status;
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

    public Boolean getStatus() {
        return this.status;
    }

    public String getStatusIcon() {
        return (status ? "X" : " "); // mark done task with X
    }
    
    @Override
    public String toString() {
        return "["+getStatusIcon()+"]" + " " + this.content;
    }


}
