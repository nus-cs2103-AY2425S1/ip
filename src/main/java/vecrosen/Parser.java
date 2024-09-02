package vecrosen;

import java.util.ArrayList;

public class Parser {
    public enum ActionType {undefined, formatting, todo, deadline, event, mark, unmark, delete, list, bye};

    /**
     * Parses the string to detect the action the user is attempting
     * and any arguments supplied by the user.
     * @param input The string the user entered
     * @param returnArgs An arraylist to hold the parsed arguments, if any
     * @return The task type the input corresponds to
     */
    public static ActionType parse(String input, ArrayList<Object> returnArgs) {
        if (input.equals("bye")) {
            return ActionType.bye;
        } else if (input.matches("mark \\d+")) {
            Integer itemNo = Integer.parseInt(input.substring(5));
            returnArgs.add(itemNo);
            return ActionType.mark;
        } else if (input.matches("unmark \\d+")) {
            Integer itemNo = Integer.parseInt(input.substring(7));
            returnArgs.add(itemNo);
            return ActionType.unmark;
        } else if (input.equals("list")) {
            return ActionType.list;
        } else if (input.matches("todo .+")) {
            String desc = input.substring(5);
            returnArgs.add(desc);
            return ActionType.todo;
        } else if (input.matches("deadline .+ /by .+")) {
            int byStart = input.indexOf("/by ");
            String by = input.substring(byStart + 4);
            String desc = input.substring(9, byStart - 1);
            returnArgs.add(desc);
            returnArgs.add(by);
            return ActionType.deadline;
        } else if (input.matches("event .+ /begin .+ /end .+")) {
            int beginStart = input.indexOf("/begin ");
            int endStart = input.indexOf("/end ");
            String desc = input.substring(6, beginStart - 1);
            String begin = input.substring(beginStart + 7, endStart - 1);
            String end = input.substring(endStart + 5);
            returnArgs.add(desc);
            returnArgs.add(begin);
            returnArgs.add(end);
            return ActionType.event;
        } else if (input.matches("event .+ /end .+ /begin .+")) {
            int beginStart = input.indexOf("/begin ");
            int endStart = input.indexOf("/end ");
            String desc = input.substring(6, endStart - 1);
            String end = input.substring(endStart + 5, beginStart - 1);
            String begin = input.substring(beginStart + 7);
            returnArgs.add(desc);
            returnArgs.add(begin);
            returnArgs.add(end);
            return ActionType.event;
        } else if (input.matches("delete \\d+")) {
            int itemNo = Integer.parseInt(input.substring(7));
            returnArgs.add(itemNo);
            return ActionType.delete;
        } // TODO: make helper function
        else if (input.startsWith("todo")) {
            returnArgs.add("todo [description]");
            return ActionType.formatting;
        } else if (input.startsWith("deadline")) {
            returnArgs.add("deadline [description] /by [deadline]");
            return ActionType.formatting;
        } else if (input.startsWith("event")) {
            returnArgs.add("event [description] /begin [startTime] /end [endTime]");
            return ActionType.formatting;
        } else if (input.startsWith("mark")) {
            returnArgs.add("mark [taskID]");
            return ActionType.formatting;
        } else if (input.startsWith("unmark")) {
            returnArgs.add("unmark [taskID]");
            return ActionType.formatting;
        } else if (input.startsWith("delete")) {
            returnArgs.add("delete [taskID]");
            return ActionType.formatting;
        } else {
            return ActionType.undefined;
        }
    }
}
