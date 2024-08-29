package cypherchatbot.task;

import cypherchatbot.task.Task;

public class ToDo extends Task {

    public ToDo(String desc) {
        super(desc);
    }

    @Override
    public String toString() {
        String str = this.completed ? "[T][X] " : "[T][ ] ";
        str += this.description;
        return str;
    }

    @Override
    public String toStringinFile() {
        int val = this.completed ? 1 : 0;
        return String.format("T|%d|%s", val, this.description);
    }
}
