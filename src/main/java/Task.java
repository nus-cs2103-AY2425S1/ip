public class Task {
    private String taskName;
    private boolean completed;
    private Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    public void markAsDone() {
        this.completed = true;
    }

    public void markAsUndone() {
        this.completed = false;
    }

    public static Task of(String name) {
        return new Task(name);
    }

    public String readTask() {
        return this.taskName;
    }

    public String getStatus(){
        return this.completed ? "X": " ";
    }


}
