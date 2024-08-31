package seedu.maxine;

import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

public class FileParser {
    
    public FileParser() {
        // nothing
    }
    public Task parse(String string) {
        String[] input = string.split(" / ");
        
        switch (input[0]) {
        case ("T"):
            Todo todo = new Todo(input[2]);
            return todo;
        case ("D"):
            Deadline deadline = new Deadline(input[2], input[3]);
            return deadline;
        case ("E"):
            Event event = new Event(input[2], input[3], input[4]);
            return event;
        }
        return null;
    }
}
