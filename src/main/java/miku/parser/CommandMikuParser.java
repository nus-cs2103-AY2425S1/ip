package miku.parser;

import miku.command.*;
import miku.exception.InvalidCommandException;
import miku.task.Deadline;
import miku.task.Event;
import miku.task.Todo;
import miku.utility.Priority;

/**
 * Parses the input command strings
 */
public class CommandMikuParser extends MikuParser {
    private static String regexMark = "mark \\d+";
    private static String regexUnmark = "unmark \\d+";
    private static String regexToDo = "todo .*";
    private static String regexDeadline = "deadline .* /by .*";
    private static String regexEvent = "event .* /.* /.*";
    private static String regexRemove = "delete \\d+";
    private static String regexFind = "find .*";
    private static String regexSetPriority = "set priority \\d+ (HIGH|MEDIUM|LOW)";

    public CommandMikuParser() {
    }

    /**
     * Parses the string input.
     *
     * @param input The string which could be a command prompt.
     * @return returns a Command to execute the prompted command.
     */
    public Command parse(String input) {
        try {
            if (input.equals("bye")) {
                return new ExitCommand();
            } else if (input.equals("list")) {
                return new ShowListCommand();
            } else if (input.matches(regexMark)) {
                return new MarkCommand(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexUnmark)) {
                return new UnmarkCommand(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexToDo)) {
                return new AddCommand(new Todo(input.substring(5)));
            } else if (input.matches(regexDeadline)) {
                String[] strs = input.split("/");
                return new AddCommand(new Deadline(strs[0].substring(9), strs[1].substring(3)));
            } else if (input.matches(regexEvent)) {
                String[] strs = input.split("/");
                return new AddCommand(new Event(strs[0].substring(6), strs[1].substring(5), strs[2].substring(3)));
            } else if (input.matches(regexRemove)) {
                //Check if removable
                return new RemoveCommand(Integer.parseInt(input.split(" ")[1]));
            } else if (input.matches(regexFind)) {
                return new FindCommand(input.substring(5));
            } else if (input.matches(regexSetPriority)) {
                String[] strs = input.split(" ");
                return new SetPriorityCommand(Integer.parseInt(strs[2]), Priority.parsePriority(strs[3]));
            } else {
                System.out.println("すみません、わかりません！\nEnter a valid command please, 39!");
                throw new InvalidCommandException(input);
            }

        } catch (InvalidCommandException e) {
            e.print();
        }

        return new InvalidCommand();

    }
}
