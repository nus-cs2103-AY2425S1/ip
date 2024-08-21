public class Task {

    private boolean status;
    private String description;

    public Task(String description) {
        this.description = description;
        this.status = false;
    }

    public Task() {}

    public boolean getStatus() {
        return this.status;
    }

    public String getDescription() {
        return this.description;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
