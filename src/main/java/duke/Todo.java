package duke;
import duke.Task;

public class Todo extends Task {
    Todo(String name) {
        super(name);
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

        message += "[T]";

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += this.name;

        System.out.println(message);
        return message;
    }

    @Override
    public String print(int rank) {
        String message = rank + ".";

        message += "[T]";

        if(this.isMarked) {
            message += "[X] ";
        } else {
            message += "[ ] ";
        }

        message += name;

        System.out.println(message);
        return message;
    }
}
