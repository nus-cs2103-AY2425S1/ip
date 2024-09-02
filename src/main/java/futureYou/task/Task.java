package futureYou.task;

public class Task {
    private boolean completed = false;
    private String name;

    public Task(String taskName) {
        this.name = taskName;
    }

    public Task(String taskName, boolean completed) {
        this.name = taskName;
        this.completed = completed;
    }

    public void markTask() {
        this.completed = true;
    }

    public String getTaskName() {
        return this.name;
    }

    public boolean taskStatus() {
        return this.completed;
    }

    public String getType() {
        return "T";
    }

    @Override
    public String toString(){
        int done = (this.taskStatus() ? 1 : 0);
        return this.getType() + "|" + done + "|" + this.getTaskName();
    }

    public String print() {
        String cross = (this.taskStatus() ? "X" : " ");
        return "[" + this.getType() + "] " + "[" + cross + "] " + this.getTaskName();
    }
}
