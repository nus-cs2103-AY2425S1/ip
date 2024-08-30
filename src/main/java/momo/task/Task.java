package momo.task;

public class Task {
    String task;
    boolean isComplete;

    public Task(String task, boolean isComplete) {
        this.task = task;
        this.isComplete = isComplete;
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

    public String toFileString() {
        return isComplete ? "0|" + task : "1|" + task;
    }

    @Override
    public String toString() {
        return isComplete ? "[X] " + task  + " ": "[ ] " + task + " ";
    }
}
