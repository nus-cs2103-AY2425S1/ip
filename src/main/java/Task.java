public class Task {
    private String description;
    private Boolean completed;

    public Task(String desc) {
        this.description = desc;
        this.completed = false;
    }

    public void completeTask() {
        this.completed = true;
    }

    public void incompleteTask() {
        this.completed = false;
    }
    @Override
    public String toString() {
        String str = this.completed ? "[X] " : "[ ] ";
        str += this.description;
        return str;
    }
}
