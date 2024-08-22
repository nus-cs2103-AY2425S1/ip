public class Task {
    private boolean isDone;
    private String name;

    public Task(String name) {
        this.isDone = false;
        this.name = name;
    }

    public boolean getIsDone() {
        return this.isDone;
    }

    public String getName() {
        return this.name;
    }

    public void mark() {
        if (this.isDone == true) {
            System.out.println("Task is already marked as done!");
        } else {
            System.out.println("Well Done Champ! I've marked this task as done!");
            this.isDone = true;
        }
    }

    public void unmark() {
        if (this.isDone == false) {
            System.out.println("Task is still undone!");
        } else {
            System.out.println("I've marked this task as undone! Please remember to complete it!");
            this.isDone = false;
        }
    }
}