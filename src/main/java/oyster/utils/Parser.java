package oyster.utils;

import java.util.ArrayList;
import java.util.Scanner;

import oyster.commands.Command;
import oyster.exceptions.ParseException;
import oyster.tasks.Task;
import oyster.tasks.TaskList;

/**
 * Handles all parsing functions.
 */
public class Parser {
    /**
     * Parses a file into a TaskList.
     *
     * @param contents Contents of file to be read.
     * @return TaskList object holding all parsed Tasks.
     * @throws ParseException If parsing goes wrong.
     */
    public static TaskList parseTaskList(String contents) throws ParseException {
        assert !contents.isBlank();

        ArrayList<Command> commands = new ArrayList<Command>();

        TaskList taskList = new TaskList();

        try {
            Scanner scanner = new Scanner(contents);

            while (scanner.hasNextLine()) {
                Task task = TaskParser.parse(scanner.nextLine());

                taskList.insert(task);
            }

            scanner.close();
        } catch (Exception e) {
            throw new ParseException();
        }

        return taskList;
    }

    /**
     * Convert TaskList into a String.
     *
     * @param taskList The TaskList to be composed into String for saving.
     * @return Writeable save String of a TaskList.
     */
    public static String composeTaskList(TaskList taskList) {
        StringBuilder builder = new StringBuilder();

        for (Task task : taskList.getItems()) {
            builder.append(TaskParser.compose(task)).append("\n");
        }

        return builder.toString();
    }

    /**
     * Parses a line into a Command.
     *
     * @param line Parses a line into a Command.
     * @return Command object.
     */
    public static Command parseCommand(String line) {
        assert !line.isBlank();

        return CommandParser.parse(line);
    }
}
