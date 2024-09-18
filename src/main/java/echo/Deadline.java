package echo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/**
 * The Deadline class represents a task with a specific deadline.
 * It extends the Task class and includes a due date for the task.
 */
public class Deadline extends Task {

    protected LocalDate by;

    /**
     * Constructs a Deadline object with the specified description and due date.
     *
     * @param description The description of the task.
     * @param by The due date of the task in the format "YYYY-MM-DD".
     * @throws DateTimeParseException If the date format is invalid.
     */
    public Deadline (String description, String by) throws DateTimeParseException{
        super(description);
        this.by = LocalDate.parse(by);
    }

    /**
     * Returns a string representation of the Deadline task.
     *
     * @return A string that includes the task type, description, and due date.
     */
    @Override
    public String toString() {
        return "[D] " + super.toString() + " (by: " + by + ")";
    }

    /**
     * Returns the due date of the task formatted for storage or display.
     *
     * @return A string representing the due date of the task.
     */
    public String getAdd(){
        return " /by " + this.by;
    }

    /**
     * Returns the type letter of the task, which is "D" for Deadline.
     *
     * @return The string "D".
     */
    @Override
    public String getTypeLetter() {
        return "D";
    }

    @Override
    public String editTask(String input) {
        String[] str = input.split(" ", 2);
        String command = str[0];


        switch (command) {
        case"name":
            this.taskDes = str[1];
            return this.toString();
        case"deadline":
            this.by = LocalDate.parse(str[1]);
        default:
            return "enter name (new name)\n or deadline (new date)";
        }

    }
}
