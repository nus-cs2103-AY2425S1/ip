package skibidi.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Deadline;
import skibidi.task.Task;
import skibidi.utils.MilitaryTime;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;
/**
 * Represents a deadline command.
 */
public class DeadlineCommand extends Command {
    public static final String COMMAND_WORD = "deadline";
    private final String description;
    private final String by;
    private final MilitaryTime time;

    /**
     * Creates a deadline command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public DeadlineCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        validateInput(input);
        assert input != null : "Input array should not be null";
        assert input.length >= 5 : "Input array should have at least 5 elements";

        try {
            int timeIndex = Arrays.asList(input).indexOf("/by") + 2;
            String formattedDeadline = formatDeadline(input);
            MilitaryTime time = new MilitaryTime(input[timeIndex]);

            this.description = everythingAfterCommand(input);
            this.by = formattedDeadline;
            this.time = time;
        } catch (DateTimeParseException e) {
            throw new SkibidiException("Error: To add a deadline task, use the format: "
                    + "deadline <description> /by <date> <time>"
                    + " where <date> is in the format yyyy-mm-dd and <time> is in the format HHmm.");
        }
    }
    /**
     * Validates the input.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public void validateInput(String[] input) throws SkibidiException {
        if (input.length < 5) { // Check if the input is empty
            throw new SkibidiException("Error: deadline description must not be empty. "
                    + "\nTo add a deadline task, use the format: deadline <description> /by <date> <time>"
                    + " where <date> is in the format yyyy-mm-dd and <time> is in the format HHmm.");
        }

        if (Arrays.stream(input).noneMatch(s -> s.equals("/by"))) {
            System.out.println(input[2]);
            throw new SkibidiException("Error: To add a deadline task, use the format: "
                    + "deadline <description> /by <date> <time>"
                    + " where <date> is in the format yyyy-mm-dd and <time> is in the format HHmm.");
        }
    }
    /**
     * Formats the deadline.
     *
     * @param input Array of input strings
     * @return The formatted deadline
     */
    public String formatDeadline(String[] input) {
        int dateIndex = Arrays.asList(input).indexOf("/by") + 1;
        LocalDate deadline = LocalDate.parse(input[dateIndex]);
        String formattedDeadline = deadline.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        return formattedDeadline;
    }

    private String everythingAfterCommand(String[] input) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/by")) {
                break;
            }
            description.append(input[i]).append(" ");
        }
        return description.toString().trim();
    }

    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        Task task = new Deadline(description, by, time);
        taskList.addTask(task);
        storage.save(taskList);
        return "Got it. I added this to the goon quest:\n"
                + "  " + task + "\n"
                + "Now you have " + taskList.size() + " quests in the list.";
    }
}
