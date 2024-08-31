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
            return new Todo(input[2]);
        case ("D"):
            return new Deadline(input[2], input[3]);
        case ("E"):
            return new Event(input[2], input[3], input[4]);
        }
        
        return null;
    }
}
