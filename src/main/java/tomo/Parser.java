package tomo;

import task.Converter;
import exception.ParserException;
import java.time.format.DateTimeParseException;

/**
 *  Understands of input command
 */

public class Parser {
    /**
     * Parses the user command
     * 
     * @param cmd The command input from user
     * @return Type of command and additional arguments to support next step
     * @throws ParserException If the command has invalid format
     */
    public String[] parse(String cmd) throws ParserException {
        if (cmd.split(" ").length == 0) return new String[]{};
        String type = cmd.split(" ")[0];
        if (type.equals("bye")) {
            if (!cmd.equals("bye")) {
                throw new ParserException("Too much arguments for bye command");
            }
            return new String[]{"bye"};
        } else if (type.equals("list")) {
            if (!cmd.equals("list")) {
                throw new ParserException("Too much arguments for list command");
            }
            return new String[]{"list"};
        } else if (type.equals("mark")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing argument idx for mark command");
            } 
            try {
                int idx = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new ParserException("Invalid idx for mark command");
            }
            return args;
        } else if (type.equals("unmark")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing argument idx for unmark command");
            } 
            try {
                int idx = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new ParserException("Invalid idx for unmark command");
            }
            return args;
        } else if (type.equals("delete")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing argument idx for delete command");
            } 
            try {
                int idx = Integer.parseInt(args[1]);
            } catch (NumberFormatException e) {
                throw new ParserException("Invalid idx for delete command");
            }
            return args;
        } else if (type.equals("todo")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing arguments for todo command");
            }
            return args;
        } else if (type.equals("deadline")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing arguments for deadline command");
            }

            String[] args2 = args[1].split(" /by ");
            if (args2.length == 1) {
                throw new ParserException("Missing arguments for deadline command");
            }
            if (args2.length >= 3) {
                throw new ParserException("Missing arguments for deadline command");
            }
            try {
                Converter.InputToDateTime(args2[1]);
            } catch (DateTimeParseException e) {
                throw new ParserException("Invalid format for argument deadline");
            }
            return new String[]{args[0], args2[0], args2[1]};
        } else if (type.equals("event")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing arguments for event command");
            }

            String[] args2 = args[1].split(" /from | /to ");
            if (args2.length <= 2) {
                throw new ParserException("Missing arguments for event command");
            }
            if (args2.length >= 4) {
                throw new ParserException("Too much arguments for event command");
            }
            
            try {
                Converter.InputToDateTime(args2[1]);
            } catch (DateTimeParseException e) {
                throw new ParserException("Invalid format for argument start");
            }

            try {
                Converter.InputToDateTime(args2[2]);
            } catch (DateTimeParseException e) {
                throw new ParserException("Invalid format for argument end");
            }
            
            return new String[]{args[0], args2[0], args2[1], args2[2]};
        } else if (type.equals("find")) {
            String[] args = cmd.split(" ", 2);
            if (args.length == 1) {
                throw new ParserException("Missing argument pattern for command find");
            }
            return args;
        } else {
            throw new ParserException("Unknown command");
        }
    }
}
