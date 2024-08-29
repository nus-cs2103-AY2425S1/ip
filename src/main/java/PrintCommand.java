import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class PrintCommand extends Command {
    private String args;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");


    public PrintCommand(String args) throws JEFFException {
        super();

        if (args.isEmpty()) {
            throw new JEFFException("You must provide a date after the command!");
        }

        this.args = args;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws JEFFException {
        try {
            LocalDate date = LocalDate.parse(args, DATE_FORMATTER);
            System.out.println("Tasks due on " + date.format(DATE_FORMATTER) + ":");
            boolean found = false;
            for (Task task : tasks.getTasks()) {
                if (task instanceof Deadline) {
                    Deadline deadlineTask = (Deadline) task;
                    if (deadlineTask.getDueDate().toLocalDate().equals(date)) {
                        System.out.println(deadlineTask);
                        found = true;
                    }
                }
            }

            if (!found) {
                System.out.println("No tasks due on this date.");
            }
        } catch (DateTimeParseException e) {
            throw new JEFFException("Invalid date format! Please use 'DD/MM/YYYY'.");
        }
    }
}