package potong;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import potong.command.AddCommand;
import potong.command.Command;
import potong.command.DeleteCommand;
import potong.command.ExitCommand;
import potong.command.FindCommand;
import potong.command.ListCommand;
import potong.command.MarkCommand;
import potong.command.TagCommand;
import potong.command.UntagCommand;
import potong.exceptions.IllegalInputPotongException;
import potong.task.DeadlineTask;
import potong.task.EventTask;
import potong.task.Task;
import potong.task.ToDoTask;




/**
 * Class with static methods to parse the user inputs.
 */
public class Parser {
    public static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");

    private static final Pattern DEADLINE_FORMAT = Pattern.compile(" (?<desc>.*)");

    /**
     * Create task from the saved tasks.
     *
     * @param taskDescription Task Description.
     * @return Task.
     */
    public static Task createTask(String taskDescription) {
        String[] arr = taskDescription.split("\\|");
        for (int i = 0; i < arr.length; i++) {
            String curr = arr[i];
            arr[i] = curr.strip();
        }
        boolean isDone = arr[1].equals("1");
        Task result = null;
        String tag = arr[2];
        String description = arr[3];
        String time = "";
        switch (arr[0]) {
        case "T":
            try {
                result = new ToDoTask(description, isDone, tag);
            } catch (IllegalInputPotongException e) {
                throw new RuntimeException(e);
            }
            break;
        case "D":
            time = arr[4];
            try {
                result = new DeadlineTask(description, time, isDone, tag);
            } catch (IllegalInputPotongException e) {
                throw new RuntimeException(e);
            }
            break;
        case "E":
            time = arr[4];
            String[] startAndEnd = time.split("-");
            try {
                result = new EventTask(description, startAndEnd[0], startAndEnd[1], isDone, tag);
            } catch (IllegalInputPotongException e) {
                throw new RuntimeException(e);
            }
            break;
        default:
            try {
                result = new Task(description, isDone, tag);
            } catch (IllegalInputPotongException e) {
                throw new RuntimeException(e);
            }
        }
        return result;
    }

    /**
     * Parse the String input command of the user.
     *
     * @param input Input command.
     * @return Command object.
     */
    public static Command parse(String input) {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(input.trim());
        if (!matcher.matches()) {

        }

        final String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments").strip();

        return switch (commandWord) {
        case "bye" -> new ExitCommand(arguments);
        case "list" -> new ListCommand(arguments);
        case "mark" -> new MarkCommand(arguments, true);
        case "unmark" -> new MarkCommand(arguments, false);
        case "delete" -> new DeleteCommand(arguments);
        case "todo" -> new AddCommand(arguments, AddCommand.Type.TODO);
        case "deadline" -> new AddCommand(arguments, AddCommand.Type.DEADLINE);
        case "event" -> new AddCommand(arguments, AddCommand.Type.EVENT);
        case "find" -> new FindCommand(arguments);
        case "tag" -> new TagCommand(arguments);
        case "untag" -> new UntagCommand(arguments);
        default -> null;
        };
    }

}
