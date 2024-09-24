package bob.task;

import bob.exception.LineCorruptedException;
import bob.exception.WrongTaskException;

public class Todo extends Task {
    public Todo(String description) {
        super(description);
    }

    @Override
    public String encode() {
        // format: T<isDone><desc>
        return "T" + (this.isDone ? "1" : "0") + this.description;
    }

    @Override
    public Task decode(String encodedString) throws WrongTaskException, LineCorruptedException {
        // format: T<isDone><desc>
        if (encodedString.charAt(0) != 'T') {
            throw new WrongTaskException();
        }

        Task task;
        try {
            task = new Todo(encodedString.substring(2));
        } catch (IndexOutOfBoundsException e) {
            throw new LineCorruptedException();
        }

        if (encodedString.charAt(1) == '1') {
            task.mark();
        } else if (encodedString.charAt(1) != '0') {
            throw new LineCorruptedException();
        }

        return task;
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }
}
