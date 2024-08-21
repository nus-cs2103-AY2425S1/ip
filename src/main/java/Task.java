public class Task {
    private String name;
    private Boolean isCompleted;


    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }


    public void markCompleted() {
        this.isCompleted = true;
    }

    public void markIncomplete() {
        this.isCompleted = false;
    }


    @Override
    public String toString() {
        String completedStatus = "";
        if (!isCompleted) {
            completedStatus += "[ ] ";
        } else {
            completedStatus += "[X] ";
        }
        return String.format(completedStatus + name);
    }
}
