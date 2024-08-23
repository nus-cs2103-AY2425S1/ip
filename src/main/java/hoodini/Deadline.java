package hoodini;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

/**
 * Class which handles deadline inputs from the user
 * and stores the deadline date.
 */
public class Deadline extends Input {
    private LocalDate deadline;

    /**
     * Constructor to store the deadline input by the user
     * @param input User input, parses user input and stores the deadline.
     * @throws DateTimeParseException If the date is not in the correct format.
     */
    public Deadline(String input) throws DateTimeParseException{
        super(input.split(" ",2)[1]
                .split("/",2)[0]);
        try {
            this.deadline = LocalDate.parse(
                    input.split("/by")[1]
                            .trim());
        } catch (DateTimeParseException e) {
            System.out.println("Please enter the date " +
                    "in the format yyyy-mm-dd");
        }


    }

    /**
     * Alternative Constructor to handle input read
     * @param input Handles the task read from file in the OS
     * @param deadline Deadline of the task
     */
    public Deadline(String input, String deadline) {
        super(input);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        this.deadline = LocalDate.parse(deadline, formatter);
    }

    /**
     * Returns the string representation of the Deadline task.
     * @return String representation of the Deadline task.
     */
    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String str = "[D] " + super.toString() + "(by: " +
                this.deadline.format(formatter) + ")";
        return str;
    }

}
