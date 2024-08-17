public class Task {
    private boolean completed;
    private String name;
    private static final String done = "[X]";
    private static final String undone = "[ ]";

    public Task(String name) {
        this.name = name;
    }

    public void mark() {
        if (completed) {
            System.out.println("Sumo confused. This task is marked as done in the first place!");
        } else {
            System.out.println("Sumo has marked this task as done.");
            this.completed = true;
        }
        System.out.println(this);
    }

    public void unmark() {
        if (!completed) {
            System.out.println("Sumo confused. This task is not completed in the first place!");
        } else {
            System.out.println("Sumo has marked this task as  NOT done.");
            this.completed = false;
        }
        System.out.println(this);
    }

    @Override
    public String toString() {
        return (completed ? done : undone) + this.name;
    }
}
