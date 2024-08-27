package Tasks;

import java.time.format.DateTimeFormatter;

public class ToDo extends Task {

    public ToDo(String description) {
        super(description);
    }

    @Override
    public String toString() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }
    @Override
    public String toFileString() {
        return "T | " + this.getStatusIcon() + " | " + this.description;
    }


}
