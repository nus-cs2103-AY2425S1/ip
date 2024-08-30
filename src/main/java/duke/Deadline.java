package duke;

import java.time.LocalDate;

public class Deadline extends Task {
    LocalDate deadl;
    public Deadline(String descr, String dl) {
        super(descr);
        deadl = LocalDate.parse(dl);
    }

    /**
     * Returns date of deadline task, when Storage.save() is called.
     *
     * @return String date.
     * */
    public String getDates() {
        return " | " + deadl;
    }

    /**
     * Returns description and date of deadline task, when TaskList.printTasks() is called.
     *
     * @return String task description and date.
     * */
    public String toString() {
        return "[D]" + super.toString() + "(by: " + super.dateConverter(deadl) + ")";
    }
}


