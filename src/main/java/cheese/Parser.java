package cheese;

import cheese.command.*;
import cheese.task.Deadline;
import cheese.task.Event;
import cheese.task.Task;
import cheese.task.ToDo;

/**
 * Parses user input
 */
public class Parser {
    /**
     * Get idx of item in list from user input
     * @param inputTokens input from user
     * @return int
     * @throws CheeseException Missing/Incorrect input
     */
    public static int getIdx(String[] inputTokens, int size) throws CheeseException {
        if (inputTokens.length != 2) throw new CheeseException("Need location of cheese");
        int idx;
        try {
            idx = Integer.parseInt(inputTokens[1]) - 1;
        } catch (NumberFormatException e) {
            throw new CheeseException(e.getMessage());
        }
        if (idx >= size || idx < 0) throw new CheeseException("Incorrect location of cheese");
        return idx;
    }

    /**
     * Parse user input
     * @param input full user input
     * @param size size of task list
     * @return command to execute
     * @throws CheeseException incorrect input
     */
    public static Command parse(String input, int size) throws CheeseException {
        String[] inputTokens = input.split(" ");
        String command = inputTokens[0];
        //Switch statement for different responses to different commands
        switch (command) {
        case "bye":
            return new ExitCommand();
        case "list":
            return new ListCommand();
        case "mark":
            int idx = getIdx(inputTokens, size);
            return new MarkCommand(idx, true);
        case "unmark":
            int i = getIdx(inputTokens, size);
            return new MarkCommand(i, false);
        case "todo":
            ToDo todo = ToDo.of(input);
            return new AddCommand(todo);
        case "deadline":
            Deadline deadline = Deadline.of(input);
            return new AddCommand(deadline);
        case "event":
            Event event = Event.of(input);
            return new AddCommand(event);
        case "delete":
            int j = getIdx(inputTokens, size);
            return new UpdateCommand(j, true);
        default:
            return new AddCommand(new Task(input));
        }
    }
}
