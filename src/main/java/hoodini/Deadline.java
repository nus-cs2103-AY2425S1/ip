package hoodini;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Input {
    private LocalDate deadline;

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

    public Deadline(String input, String deadline) {
        super(input);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        this.deadline = LocalDate.parse(deadline, formatter);
    }

    @Override
    public String toString() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d yyyy");
        String str = "[D] " + super.toString() + "(by: " +
                this.deadline.format(formatter) + ")";
        return str;
    }

}
