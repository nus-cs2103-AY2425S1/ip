package duke;
import duke.Task;

public class Deadline extends Task {
    Deadline(String name, String deadline) {
        super(name);
        this.deadline = deadline;
    }

    @Override
    public void mark() {
        this.isMarked = true;
    }

    @Override
    public void unmark() {
        this.isMarked = false;
    }

    @Override
    public String print() {
        String message = "";

        message += "[D]";

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += this.name;

        message += " (by: " + this.deadline + ")";

        System.out.println(message);
        return message;
    }

    @Override
    public String print(int rank) {
        String message = rank + ".";

        message += "[D]";

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += name;

        message += " (by: " + deadline + ")";

        System.out.println(message);
        return message;
    }

    public String deadline;
}
