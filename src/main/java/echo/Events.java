package echo;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;


/**
 * The Events class represents a task that has a start and end date.
 * It extends the Task class and includes additional fields to store the start and end dates.
 */
public class Events extends Task {


    LocalDate start;
    LocalDate end;

    /**
     * Constructs an Events task with the specified description, start date, and end date.
     *
     * @param taskDes The description of the event task.
     * @param start   The start date of the event, in the format "yyyy-MM-dd".
     * @param end     The end date of the event, in the format "yyyy-MM-dd".
     * @throws DateTimeParseException If the date format is incorrect.
     */
    public Events(String taskDes, String start, String end) throws DateTimeParseException {
        super(taskDes);
        this.start = LocalDate.parse(start);
        this.end = LocalDate.parse(end);
    }

    /**
     * Returns a string representation of the Events task.
     *
     * @return A string that includes the task type, description, start date, and end date.
     */
    @Override
    public String toString() {
        return "[E] " + super.toString() + " (from: " + start + " to: " + end + ")";
    }

    /**
     * Returns the additional details of the Events task, including the start and end dates.
     *
     * @return A string in the format " /from start /to end".
     */
    @Override
    public String getAdd() {
        return " /from " + start + " /to " + end;
    }

    /**
     * Returns the type letter representing the Events task.
     *
     * @return The letter "E" for Events.
     */
    @Override
    public String getTypeLetter() {
        return "E";
    }


    @Override
    public String editTask(String input) {
        String[] str = input.split(" ", 2);
        String command = str[0];


        switch (command) {
        case"name":
            this.taskDes = str[1];
            return this.toString();
        case"start":
            this.start = LocalDate.parse((str[1]));
        case"end":
            this.end = LocalDate.parse(str[1]);
        default:
            return "enter name (new name)\n or start (new date)\n or end (new date)";
        }

    }

}
