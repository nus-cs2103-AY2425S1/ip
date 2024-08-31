import java.util.Objects;

public abstract class Task {

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
        //System.out.println(line + "Nice! I've marked this task as done:\n"
          //      + this.displayTask() + line);
    }

    public String markedNotification() {
        return line + "Nice! I've marked this task as done:\n"
                + this.displayTask() + line;
    }

    public void markAsUndone() {
        this.done = false;
        System.out.println(line + "OK, I've marked this task as not done yet:\n"
                + this.displayTask() + line);
    }
    
    public static Task intepreteTask(String description, String type) {
        if (Objects.equals(type, "T")) {    // To Do
            return new ToDo(description);
        } else if (Objects.equals(type, "D")) {     // Deadline
            String deadline = description.split("/by")[1];
            String input = description.split("/by")[0];
            return new Deadline(input, deadline);
        } else {        // Event
            String input = description.split("/from")[0];
            String period = description.split("/from")[1];
            String start = period.split("/to")[0];
            String end = period.split("/to")[1];
            return new Event(input, start, end);
        }
    }
}
