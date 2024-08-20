public class Task {
    String task;
    boolean isComplete = false;

    public Task(String task) {
        this.task = task;
    }

    public void markComplete() {
        this.isComplete = true;
        System.out.println("Nice! I've marked this task as done");
        System.out.println(this);
    }

    public void unmark() {
        this.isComplete = false;
        System.out.println("OK, I've marked this task as not done yet");
        System.out.println(this);
    }

    @Override
    public String toString() {
        return isComplete ? "[X] " + task : "[ ] " + task;
    }
}
