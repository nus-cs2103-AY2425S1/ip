package skibidi.command;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;

import skibidi.exceptions.SkibidiException;
import skibidi.task.Event;
import skibidi.task.Task;
import skibidi.utils.MilitaryTime;
import skibidi.utils.Storage;
import skibidi.utils.TaskList;

/**
 * Represents an event command.
 */
public class EventCommand extends Command {

    public static final String COMMAND_WORD = "event";
    private final String description;
    private final String from;
    private final String to;
    private final MilitaryTime startTime;
    private final MilitaryTime endTime;

    /**
     * Creates an event command.
     *
     * @param input Array of input strings
     * @throws SkibidiException If the input is invalid
     */
    public EventCommand(String[] input) throws SkibidiException {
        super(COMMAND_WORD);
        if (input.length < 8) {
            throw new SkibidiException("Error: event description must not be empty. "
                    + "\nTo add an event task, "
                    + "use the format: event <description> /from <date> <time> /to <date> <time>");
        }

        if (Arrays.stream(input).noneMatch(s -> s.equals("/from"))
                || Arrays.stream(input).noneMatch(s -> s.equals("/to"))) {
            throw new SkibidiException("Error: event description must not be empty. "
                    + "\nTo add an event task, "
                    + "use the format: event <description> /from <date> <time> /to <date> <time>");
        }

        int startFromIndex = Arrays.asList(input).indexOf("/from") + 1;
        int endsAtIndex = Arrays.asList(input).indexOf("/to") + 1;
        LocalDate startFrom = LocalDate.parse(input[startFromIndex]);
        String formattedStartFrom = startFrom.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));
        LocalDate endsAt = LocalDate.parse(input[endsAtIndex]);
        String formattedEndsAt = endsAt.format(DateTimeFormatter.ofPattern("MMM dd yyyy"));

        this.description = everythingAfterCommand(input);
        this.from = formattedStartFrom;
        this.to = formattedEndsAt;
        this.startTime = new MilitaryTime(input[startFromIndex + 1]);
        this.endTime = new MilitaryTime(input[endsAtIndex + 1]);
    }

    private String everythingAfterCommand(String[] input) {
        StringBuilder description = new StringBuilder();
        for (int i = 1; i < input.length; i++) {
            if (input[i].equals("/from")) {
                break;
            }
            description.append(input[i]).append(" ");
        }
        return description.toString().trim();
    }
    @Override
    public String execute(Storage storage, TaskList taskList) throws IOException {
        Task task = new Event(description, from, startTime, to, endTime);
        taskList.addTask(task);
        storage.save(taskList);
        return "Got it. I added this to the goon quest:\n" + task + "\nNow you have " + taskList.size()
                + " quests in the list.";
    }
}
