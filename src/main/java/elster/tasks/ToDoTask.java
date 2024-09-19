package elster.tasks;

import elster.Elseption;

/**
 * Todo tasks are basic tasks with regular task functionalities
 */
public class ToDoTask extends Task {
    public ToDoTask(String description) {
        super(description);
    }

    /**
     * Factory method for a todo task.
     * Parses input string to create a todo task with a description.
     *
     * @param input Input from terminal to be parsed.
     * @return Created todo task.
     */
    public static ToDoTask of(String input) throws Elseption {
        try {
            return new ToDoTask(input.substring(5).strip());
        } catch (StringIndexOutOfBoundsException e) {
            throw new Elseption("Hello hello, there seems to be something wrong with your todo "
                    + "description, try again?");
        }
    }

    @Override
    public String toString() {
        StringBuilder resultStr = new StringBuilder();
        if (this.status) {
            resultStr.append("[T][X] ").append(this.description);
        } else {
            resultStr.append("[T][ ] ").append(this.description);
        }
        for (String tag : tags) {
            resultStr.append(" #").append(tag);
        }
        return resultStr.toString();
    }

    /**
     * Returns a string representation of the todo task suitable for writing to the save file.
     *
     * @return String representation of todo task formatted for file writing.
     */
    @Override
    public String toFileString() {
        if (this.status) {
            return "T | 1 | " + this.description + " | " + this.tags;
        } else {
            return "T | 0 | " + this.description + " | " + this.tags;
        }
    }
}
