package Data;

import Data.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ToDos extends Task {
    public ToDos(String content) {

        super(content);
    }
    /**
     * Return a brief description of task
     * @return
     */
    @Override
    public String brief() {
        return "D | " + super.getStatus() + " | " + super.getTask();
    }
    @Override
    public LocalDateTime endTime() {
        return LocalDateTime.MAX;
    }
    @Override
    public String toString() {
        return "[T] " + super.toString();
    }
}
