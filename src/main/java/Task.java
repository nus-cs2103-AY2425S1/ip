public class Task {
    public String taskName;
    public boolean completed;
    private Task(String name) {
        this.taskName = name;
        this.completed = false;
    }

    public static Task of(String name) {
        return new Task(name);
    }

    public String readTask() {
        return this.taskName;
    }


}
