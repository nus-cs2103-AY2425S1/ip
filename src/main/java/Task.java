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
            System.out.println("Task is already marked as done!")
        } else {
            this.isDone = true;
        }
    }

    public void unmark() {
        if (this.isDone == false) {
            System.out.println("Task is still undone!")
        } else {
            this.isDone = false;
        }
    }
}