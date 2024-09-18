package seedu.maxine.parser;

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
            Task todo = new Todo(input[2]);
            if (input[1].equals("1")) {
                todo.markDone();
            }
            return todo;
        case ("D"):
            Task deadline = new Deadline(input[2], input[3]);
            if (input[1].equals("1")) {
                deadline.markDone();
            }
            return deadline;
        case ("E"):
            Task event = new Event(input[2], input[3], input[4]);
            if (input[1].equals("1")) {
                event.markDone();
            }
            return event;
        default:
            return null;
        }
    }
}
