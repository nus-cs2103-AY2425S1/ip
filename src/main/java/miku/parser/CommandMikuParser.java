package miku.parser;

import miku.command.*;
import miku.exception.InvalidCommandException;
import miku.task.Deadline;
import miku.task.Event;
import miku.task.Todo;

public class CommandMikuParser extends MikuParser {
    public static String regexMark = "mark \\d+";
    public static String regexUnmark = "unmark \\d+";
    public static String regexToDo = "todo .*";
    public static String regexDeadline = "deadline .* /by .*";
    public static String regexEvent = "event .* /.* /.*";
    public static String regexRemove = "delete \\d+";

    public CommandMikuParser(){
    }

    public Command parse(String input){
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
            } else {
                System.out.println("すみません、わかりません！\nEnter a valid command please, 39!");
                throw new InvalidCommandException(input);
            }
        } catch (InvalidCommandException e) {
            e.print();
        }

        return null;

    }
}
