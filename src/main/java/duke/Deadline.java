package duke;

import java.time.LocalDate;
/**
 * Represents a Task with a specified deadline.
 */
public class Deadline extends Task {
    LocalDate date;
    private static final String type = "[D]";
    public Deadline(String descr, String dl) {
        super(descr);
        date = LocalDate.parse(dl);
    }

    /**
     * Returns date of deadline task, when Storage.save() is called.
     *
     * @return String date.
     * */
    public String getDates() {
        return " | " + date;
    }

    /**
     * Returns description and date of deadline task, when TaskList.printTasks() is called.
     *
     * @return String task description and date.
     * */
    public String toString() {
        return type + super.toString() + "(by: " + super.dateConverter(date) + ")";
    }
}


