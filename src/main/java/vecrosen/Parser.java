package vecrosen;

import java.util.ArrayList;

/**
 * Class containing a static method to parse user inputs into the requested action and arguments
 */
public class Parser {
    /** Action types recognized by Vecrosen */
    public enum ActionType { UNDEFINED, FORMATTING, TODO, DEADLINE, EVENT, MARK, UNMARK, DELETE, LIST, BYE, FIND };
    private static String badFormattingMessage;

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
            getArgs(input, returnArgs, 5, true);
            return ActionType.MARK;
        } else if (input.matches("unmark \\d+")) {
            getArgs(input, returnArgs, 7, true);
            return ActionType.UNMARK;
        } else if (input.equals("list")) {
            return ActionType.LIST;
        } else if (input.matches("todo .+")) {
            getArgs(input, returnArgs, 5, false);
            return ActionType.TODO;
        } else if (input.matches("deadline .+ /by .+")) {
            getArgs(input, returnArgs, 9, false, "/by");
            return ActionType.DEADLINE;
        } else if (input.matches("event .+ /begin .+ /end .+")) {
            getArgs(input, returnArgs, 6, false, "/begin", "/end");
            return ActionType.EVENT;
        } else if (input.matches("event .+ /end .+ /begin .+")) {
            getArgs(input, returnArgs, 6, false, "/end", "/begin");
            returnArgs.add(returnArgs.get(returnArgs.size() - 2));
            returnArgs.remove(returnArgs.size() - 3);
            return ActionType.EVENT;
        } else if (input.matches("find .+")) {
            getArgs(input, returnArgs, 5, false);
            return ActionType.FIND;
        } else if (input.matches("delete \\d+")) {
            getArgs(input, returnArgs, 7, true);
            return ActionType.DELETE;
        } else if (checkBadFormatting(input)) {
            returnArgs.add(badFormattingMessage);
            return ActionType.FORMATTING;
        } else {
            return ActionType.UNDEFINED;
        }
    } // cannot feasibly be shortened.

    private static void getArgs(String input, ArrayList<Object> returnArgs, int firstArg,
                                boolean isIntFirst, String... argTags) {
        int noOfArgs = argTags.length + 1;
        int[] argStarts = new int[noOfArgs];
        int[] argEnds = new int[noOfArgs];
        argEnds[noOfArgs - 1] = input.length();
        argStarts[0] = firstArg;
        for (int i = 0; i < noOfArgs - 1; ++i) {
            argEnds[i] = input.indexOf(" " + argTags[i] + " ");
            argStarts[i + 1] = argEnds[i] + argTags[i].length() + 2;
        }
        for (int i = 0; i < noOfArgs; ++i) {
            returnArgs.add(input.substring(argStarts[i], argEnds[i]));
        }
        if (isIntFirst) {
            returnArgs.set(0, Integer.parseInt((String) returnArgs.get(0)));
        }
    }

    private static boolean checkBadFormatting(String input) {
        if (input.startsWith("todo")) { // TODO: make helper function
            badFormattingMessage = "todo [description]";
        } else if (input.startsWith("deadline")) {
            badFormattingMessage = "deadline [description] /by [deadline]";
        } else if (input.startsWith("event")) {
            badFormattingMessage = "event [description] /begin [startTime] /end [endTime]";
        } else if (input.startsWith("mark")) {
            badFormattingMessage = "mark [taskID]";
        } else if (input.startsWith("unmark")) {
            badFormattingMessage = "unmark [taskID]";
        } else if (input.startsWith("delete")) {
            badFormattingMessage = "delete [taskID]";
        } else {
            return false;
        }
        return true;
    }
}
