package Azir;

import Azir.AzirException;

public class Parser {
    public static String[] parse(String command) throws AzirException {
        if (command.equals("list")) {
            return new String[] {"list"};
        } else if (command.equals("bye")) {
            return new String[] {"bye"};
        }
        else if (command.startsWith("mark")) {
            return command.split(" ");
        } else if (command.startsWith("unmark")) {
            return command.split(" ");
        } else if (command.startsWith("delete")) {
            return command.split(" ");
        }
        else if (command.startsWith("todo")) {
            String description = command.substring(5);
            return new String[] {"todo", description};
        } else if (command.startsWith("deadline")) {
            int byIndex = command.indexOf("/by");
            String description = command.substring(9, byIndex - 1);
            String day = command.substring(byIndex + 4);
            return new String[] {"deadline", description, day};
        } else if (command.startsWith("event")) {
            int fromIndex = command.indexOf("/from");
            int toIndex = command.indexOf("/to");
            String description = command.substring(6, fromIndex - 1);
            String startDay = command.substring(fromIndex + 6, toIndex - 1);
            String endDay = command.substring(toIndex + 4);
            return new String[] {"event", description, startDay, endDay};
        } else {
            throw new AzirException("Azir does not take in this input");
        }
    }
}
