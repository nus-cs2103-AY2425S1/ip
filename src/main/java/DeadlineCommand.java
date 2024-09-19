
import java.time.format.DateTimeParseException;

public class DeadlineCommand extends Command{

    private DeadlineCommand(Deadline deadline) {
        this.deadline = deadline;
    }
    private final Deadline deadline;

    public static DeadlineCommand of(String input) throws LewisException {

        try {
            //Get the rest of the command input
            String[] tokens = input.split("deadline");

            //Separate the deadline info into description, date and time
            String[] deadlineInfo = tokens[1].split("/by");
            String deadlineDescription = deadlineInfo[0].trim();
            String date;
            String time = "23:59"; //Default deadline time
            if (deadlineInfo[1].trim().length() > 10) { //Check if user only entered the date
                String[] dateAndTime = deadlineInfo[1].split(" ");
                date = dateAndTime[0].trim();
                time = dateAndTime[1].trim();
            } else {
                date = deadlineInfo[1].trim();
            }

            Deadline newDeadline = Deadline.of(deadlineDescription, date, time);
            return new DeadlineCommand(newDeadline);
        } catch (IndexOutOfBoundsException | DateTimeParseException e) {
            throw new LewisException("Hey, I don't understand your command! Type your " +
                    "command in this format:\n" +
                    "deadline <description> /by YYYY-MM-DD HH:MM");
        }
    }

        public static String getHelpDescription() {
        return """
                Enters a new deadline into the tasklist.
                 Usage: deadline <description> /by <date> <time> where\s
                <date> is in format YYYY-MM-DD and\s
                <time> is in format HH:MM
                If time is not specified, the default value is 23:59""";
    }

    /**
     * Parses the user input as a deadline and enters it into the tasklist
     */
    @Override
    public void execute() {
        TaskList.add(deadline);
        Ui.printTask(deadline);
        Storage.save();
    }
}
