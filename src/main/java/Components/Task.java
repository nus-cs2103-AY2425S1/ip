package Components;

public class Task {
    private boolean done;
    private String text;

    public Task(String textString) {
        this.done = false;
        this.text = textString;
    }

    public void completeTask() {
        this.done = true;
    }

    public void uncompleteTask() {
        this.done = false;
    }

    @Override
    public String toString() {
        return "[" + (done ? "X" : " ") + "] " + this.text;
    }
}
