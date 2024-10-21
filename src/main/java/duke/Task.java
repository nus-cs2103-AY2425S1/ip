package duke;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;


public class Task {

    public Task() {}

    public Task(String name) {
        this.name = name;
        this.isMarked = false;
    }

    public void mark() {
        this.isMarked = true;
    }

    public void unmark() {
        this.isMarked = false;
    }

    public String print() {
        String message = "";
        return message;
    }

    public String print(int rank) {
        return "";
    }
    public String name;
    public boolean isMarked;
}
