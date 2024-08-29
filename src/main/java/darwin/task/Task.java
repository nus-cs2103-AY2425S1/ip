package darwin.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public abstract class Task {
    private String name;
    private boolean done = false;

    public Task(String name) {
        this.name = name;
    }
    public String getName() {
        return this.name;
    }
    public boolean isDone() {
        return this.done;
    }
    public void markDone() {
        this.done = true;
    }
    public void unmarkDone() {
        this.done = false;
    }
    public abstract String getSymbol();
    public String toCsv() {
        String check = this.isDone() ? "1" : "0";
        return this.getSymbol() + "," + check + "," + this.getName();
    }
    public String formatDate(LocalDateTime date) {
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return date.format(dateFormatter);
    }
    public String getTaskInfo() {
        String check = this.isDone() ? "X" : " ";
        return String.format("[%s][%s] %s", this.getSymbol(), check, this.getName());
    }
}
