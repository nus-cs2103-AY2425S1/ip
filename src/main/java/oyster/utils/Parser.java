package oyster.utils;

import oyster.commands.*;
import oyster.exceptions.ParseException;
import oyster.tasks.*;

import java.util.ArrayList;
import java.util.Scanner;

public class Parser {
    public static TaskList parseTaskList(String contents) throws ParseException {
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

    public static String composeTaskList(TaskList taskList) {
        StringBuilder builder = new StringBuilder();

        for (Task task : taskList.getItems()) {
            builder.append(TaskParser.compose(task)).append("\n");
        }

        return builder.toString();
    }

    public static Command parseCommand(String line) {
        return CommandParser.parse(line);
    }
}
