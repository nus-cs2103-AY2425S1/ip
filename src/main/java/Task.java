public class Task {

    private String input;
    private boolean done;
    String line = "____________________________________________________________\n";

    public Task(String input) {
        this.input = input;
        this.done = false;
    }

    public String addedString() {
        return "Got it. I've added this task:\n" + displayTask();
    }

    public String displayTask() {
        if (done) {
            return "[X] " + this.input + "\n";
        } else {
            return "[ ] " + this.input + "\n";
        }
    }

    public String getInput() {
        return this.input;
    }

    public boolean getDone() {
        return this.done;
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
