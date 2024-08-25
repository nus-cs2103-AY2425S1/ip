package Components;
import java.util.InputMismatchException;

public class Deadline extends Task {
    
    private String deadline;

    private Deadline(String description, String deadline) {
        super(description); 
        this.deadline = deadline; 
    }

    public static Deadline createNewDeadline(String textString) throws InputMismatchException {
        if (!textString.matches("^\\s+\\S.*\\s*/by\\s*\\S.*")) {
            throw new InputMismatchException("Ensure that input contains description and end date");
        }

        String description = textString.replaceAll("/by.*", "").trim();
        String deadline = textString.replaceAll(".*/by", "").trim();
        return new Deadline(description, deadline);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
