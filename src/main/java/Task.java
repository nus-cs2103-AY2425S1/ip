public class Task {

    private String input;
    private static int numOfTasks = 0;
    private int index;
    private boolean done;
    String line = "____________________________________________________________\n";

    public Task(String input) {
        this.input = input;
        numOfTasks++;
        this.index = numOfTasks - 1;
        this.done = false;
    }

    public String addedString() {
        return line + "added: " + input + "\n" + line;
    }

    public String displayTask() {
        if (done) {
            return "[X] " + this.input + "\n";
        } else {
            return "[ ] " + this.input + "\n";
        }
    }

    public int getIndex() {
        return this.index;
    }

    public void markAsDone() {
        this.done = true;
        System.out.println(line + "Nice! I've marked this task as done:\n"
                + this.displayTask() + line);
    }

    public void markAsUndone() {
        this.done = false;
        System.out.println(line + "OK, I've marked this task as not done yet:\n"
                + this.displayTask() + line);
    }

}
