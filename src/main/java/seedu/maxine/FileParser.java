package seedu.maxine;

import seedu.maxine.task.Deadline;
import seedu.maxine.task.Event;
import seedu.maxine.task.Task;
import seedu.maxine.task.Todo;

public class FileParser {

    /**
     * Constructs new instance of FileParser class
     */
    public FileParser() {
        // nothing
    }

    /**
     * Returns a Task (Todo, Deadline or Event) based on the string from the txt file
     * If the file is empty, return null
     * @param string String from txt file to be parsed
     * @return Task which matches the input value
     */
    public Task parse(String string) {
        String[] input = string.split(" / ");
        switch (input[0]) {
        case ("T"):
            return new Todo(input[2]);
        case ("D"):
            return new Deadline(input[2], input[3]);
        case ("E"):
            return new Event(input[2], input[3], input[4]);
        default:
            return null;
        }
    }
}
