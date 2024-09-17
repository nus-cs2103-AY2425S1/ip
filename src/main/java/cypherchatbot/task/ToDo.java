package cypherchatbot.task;

import java.util.Objects;

public class ToDo extends Task {


    public ToDo(String desc) {
        super(desc,1);
    }

    @Override
    public String toString() {
        String str = this.isComplete ? "[T][X] " : "[T][ ] ";
        str += this.description;
        return str;
    }

    @Override
    public String toStringInFile() {
        int val = this.isComplete ? 1 : 0;
        return String.format("T|%d|%s", val, this.description);
    }

    @Override
    public boolean equals(Object o) {
        if (o.getClass() == this.getClass()) {
            return Objects.equals(this.description, ((ToDo) o).description);
        }
        return false;
    }
}
