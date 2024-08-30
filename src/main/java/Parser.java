import exception.MaxineException;
import task.Deadline;
import task.Event;
import task.Task;
import task.Todo;

import java.util.ArrayList;

public class Parser {
    
    public Parser() {
        // nothing
    }
    public Task parse(String string) throws MaxineException {
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
        throw new MaxineException("No Task found!");
    }
}
