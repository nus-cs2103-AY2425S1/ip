public class Task {
    private String name;
    private Boolean isCompleted;


    public Task(String name) {
        this.name = name;
        this.isCompleted = false;
    }

    public void complete() {
        this.isCompleted = true;
    }

    public void uncomplete() {
        this.isCompleted = false;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        String marker = isCompleted ? "[X]" : "[ ]";
        return marker + " " + this.name;
    }

}