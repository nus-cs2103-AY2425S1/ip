package Components;

public class Deadline extends Task {
    
    private String deadline;

    public Deadline(String textString) {
        super(textString.replaceAll(" /.*", "")); /* isolates the task description */

        //TODO: add exception check to make sure that /by is present
        
        this.deadline = textString.replaceAll(".*/by ", ""); /* isolates the task deadline */
    }

    @Override
    public String toString() {
        return "[D]" + super.toString() + " (by: " + this.deadline + ")";
    }
}
