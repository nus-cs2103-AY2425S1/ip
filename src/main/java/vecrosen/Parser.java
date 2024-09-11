package vecrosen;

import java.util.ArrayList;

/**
 * Class containing a static method to parse user inputs into the requested action and arguments
 */
public class Parser {
    /** Action types recognized by Vecrosen */
    public enum ActionType { UNDEFINED, FORMATTING, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, list, BYE, FIND };

    /**
     * Parses the string to detect the action the user is attempting
     * and any arguments supplied by the user.
     *
     * @param input The string the user entered
     * @param returnArgs An arraylist to hold the parsed arguments, if any
     * @return The task type the input corresponds to
     */
    public static ActionType parse(String input, ArrayList<Object> returnArgs) {
        if (input.equals("bye")) {
            return ActionType.BYE;
        } else if (input.matches("mark \\d+")) {
            Integer itemNo = Integer.parseInt(input.substring(5));
            returnArgs.add(itemNo);
            return ActionType.MARK;
        } else if (input.matches("unmark \\d+")) {
            Integer itemNo = Integer.parseInt(input.substring(7));
            returnArgs.add(itemNo);
            return ActionType.UNMARK;
        } else if (input.equals("list")) {
            return ActionType.list;
        } else if (input.matches("todo .+")) {
            String desc = input.substring(5);
            returnArgs.add(desc);
            return ActionType.TODO;
        } else if (input.matches("deadline .+ /by .+")) {
            int byStart = input.indexOf("/by ");
            String by = input.substring(byStart + 4);
            String desc = input.substring(9, byStart - 1);
            returnArgs.add(desc);
            returnArgs.add(by);
            return ActionType.DEADLINE;
        } else if (input.matches("event .+ /begin .+ /end .+")) {
            int beginStart = input.indexOf("/begin ");
            int endStart = input.indexOf("/end ");
            String desc = input.substring(6, beginStart - 1);
            String begin = input.substring(beginStart + 7, endStart - 1);
            String end = input.substring(endStart + 5);
            returnArgs.add(desc);
            returnArgs.add(begin);
            returnArgs.add(end);
            return ActionType.EVENT;
        } else if (input.matches("event .+ /end .+ /begin .+")) {
            int beginStart = input.indexOf("/begin ");
            int endStart = input.indexOf("/end ");
            String desc = input.substring(6, endStart - 1);
            String end = input.substring(endStart + 5, beginStart - 1);
            String begin = input.substring(beginStart + 7);
            returnArgs.add(desc);
            returnArgs.add(begin);
            returnArgs.add(end);
            return ActionType.EVENT;
        } else if (input.matches("find .+")) {
            String keyword = input.substring(5);
            returnArgs.add(keyword);
            return ActionType.FIND;
        } else if (input.matches("delete \\d+")) {
            int itemNo = Integer.parseInt(input.substring(7));
            returnArgs.add(itemNo);
            return ActionType.DELETE;
        } else if (input.startsWith("todo")) { // TODO: make helper function
            returnArgs.add("todo [description]");
            return ActionType.FORMATTING;
        } else if (input.startsWith("deadline")) {
            returnArgs.add("deadline [description] /by [deadline]");
            return ActionType.FORMATTING;
        } else if (input.startsWith("event")) {
            returnArgs.add("event [description] /begin [startTime] /end [endTime]");
            return ActionType.FORMATTING;
        } else if (input.startsWith("mark")) {
            returnArgs.add("mark [taskID]");
            return ActionType.FORMATTING;
        } else if (input.startsWith("unmark")) {
            returnArgs.add("unmark [taskID]");
            return ActionType.FORMATTING;
        } else if (input.startsWith("delete")) {
            returnArgs.add("delete [taskID]");
            return ActionType.FORMATTING;
        } else {
            return ActionType.UNDEFINED;
        }
    }
}
